package com.picpay.desafio.android.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.model.network.dto.UserDto
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_user.view.*

class UserListItemViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    fun bind(userDto: UserDto) {
        itemView.name.text = userDto.name
        itemView.username.text = userDto.username
        itemView.progressBar.visibility = View.VISIBLE
        Picasso.get()
            .load(userDto.img)
            .error(R.drawable.ic_round_account_circle)
            .into(itemView.picture, object : Callback {

                override fun onSuccess() {
                    itemView.progressBar.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    itemView.progressBar.visibility = View.GONE
                }
            })
    }
}