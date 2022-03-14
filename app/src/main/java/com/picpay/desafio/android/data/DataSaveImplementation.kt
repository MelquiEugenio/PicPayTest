package com.picpay.desafio.android.data

import android.content.Context
import android.content.SharedPreferences
import com.picpay.desafio.android.data.network.dto.UserDto
import com.picpay.desafio.android.domain.DataSave
import com.picpay.desafio.android.utils.Constants
import org.json.JSONObject
import javax.inject.Inject

class DataSaveImplementation @Inject constructor(context: Context): DataSave {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(Constants().SHARED_PREFERENCES, Context.MODE_PRIVATE)

    override fun saveUsers(users: List<UserDto>) {

        val userSet: MutableSet<String> = mutableSetOf()

        users.forEach {
            userSet.add(it.toJsonString())
        }

        with (sharedPreferences.edit()) {
            putStringSet("users", userSet)
            apply()
        }
    }

    override fun getUsers(): List<UserDto>? {
        val usersSet = sharedPreferences.getStringSet("users", null)
        val users: MutableList<UserDto> = mutableListOf()

        if (usersSet == null) return null

        usersSet.forEach {
            val json = JSONObject(it)
            users.add(
                UserDto(
                    json["img"].toString(),
                    json["name"].toString(),
                    json["id"] as Int,
                    json["username"].toString(),
                )
            )
        }

        return users
    }
}