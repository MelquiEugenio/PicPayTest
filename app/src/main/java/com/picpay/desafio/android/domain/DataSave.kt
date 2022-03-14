package com.picpay.desafio.android.domain

import com.picpay.desafio.android.data.network.dto.UserDto

interface DataSave {

    fun saveUsers(users: List<UserDto>)

    fun getUsers(): List<UserDto>?
}