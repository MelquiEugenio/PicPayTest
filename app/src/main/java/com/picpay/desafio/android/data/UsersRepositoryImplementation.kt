package com.picpay.desafio.android.data

import com.picpay.desafio.android.data.network.api.PicPayService
import com.picpay.desafio.android.data.network.dto.UserDto
import com.picpay.desafio.android.domain.DataSave
import com.picpay.desafio.android.domain.UsersRepository
import javax.inject.Inject

class UsersRepositoryImplementation @Inject constructor(
    private val service: PicPayService,
    private val dataSave: DataSave
): UsersRepository {

    override suspend fun getUsersFromApi(): List<UserDto>? {
        return try {
            service.getUsers()
        } catch (e: Exception) {
            null
        }
    }

    override fun saveUsers(users: List<UserDto>) {
        dataSave.saveUsers(users)
    }

    override fun getSavedUsers(): List<UserDto>? {
        return dataSave.getUsers()
    }
}