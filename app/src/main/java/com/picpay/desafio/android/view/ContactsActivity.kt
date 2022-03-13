package com.picpay.desafio.android.view

import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.viewmodel.ContactsViewModel
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
                val message = getString(R.string.error)
                progressBar.visibility = View.GONE
                recyclerView.visibility = View.GONE

                Toast.makeText(this@ContactsActivity, message, Toast.LENGTH_SHORT)
                    .show()

            } else {
                progressBar.visibility = View.GONE
                adapter.users = it
            }
        }
    }
}