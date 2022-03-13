package com.picpay.desafio.android.data

import com.picpay.desafio.android.data.network.api.PicPayService
import com.picpay.desafio.android.data.network.dto.User
import com.picpay.desafio.android.domain.UsersRepository
import javax.inject.Inject

class UsersRepositoryImplementation @Inject constructor(
    private val service: PicPayService
): UsersRepository {

    override suspend fun getUsers(): List<User>? {
        return try {
            service.getUsers()
        } catch (e: Exception) {
            null
        }
    }
}