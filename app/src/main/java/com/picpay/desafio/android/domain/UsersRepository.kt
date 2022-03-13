package com.picpay.desafio.android.domain

import com.picpay.desafio.android.data.network.dto.User

interface UsersRepository {

    suspend fun getUsers(): List<User>?
}