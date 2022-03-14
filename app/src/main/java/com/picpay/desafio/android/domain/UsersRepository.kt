package com.picpay.desafio.android.domain

import com.picpay.desafio.android.data.network.dto.UserDto


/**
 * Repository to handle api requests for users data.
 */
interface UsersRepository {

    suspend fun getUsers(): List<UserDto>?
}