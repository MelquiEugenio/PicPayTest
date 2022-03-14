package com.picpay.desafio.android.presenter

import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.data.network.dto.UserDto
import com.picpay.desafio.android.presenter.adapter.UserListAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ContactsActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewModel: ContactsViewModel by viewModels()

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: UserListAdapter

    override fun onResume() {
        super.onResume()

        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.user_list_progress_bar)
        adapter = UserListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        progressBar.visibility = View.VISIBLE

        viewModel.users.observe(this) {
            if (it == null) {
                progressBar.visibility = View.GONE
                recyclerView.visibility = View.GONE

                Toast.makeText(this@ContactsActivity, getString(R.string.error), Toast.LENGTH_SHORT)
                    .show()

                // Try to get users from data saved.
                val users: List<UserDto>? = viewModel.getSavedUsers()
                if (users != null && users.isNotEmpty()) {

                    Toast.makeText(
                        this@ContactsActivity,
                        getString(R.string.offline_data),
                        Toast.LENGTH_SHORT
                    )
                        .show()

                    recyclerView.visibility = View.VISIBLE
                    adapter.users = users
                }

            } else if (it.isNotEmpty()) {
                viewModel.saveUsers(it)
                progressBar.visibility = View.GONE
                adapter.users = it
            }
        }
    }
}