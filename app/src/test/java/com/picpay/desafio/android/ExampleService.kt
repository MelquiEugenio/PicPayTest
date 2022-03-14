package com.picpay.desafio.android

import com.picpay.desafio.android.data.network.api.PicPayService
import com.picpay.desafio.android.data.network.dto.UserDto

class ExampleService(
    private val service: PicPayService
) {

    fun example(): List<UserDto> {
        val users = service.getUsers().execute()

        return users.body() ?: emptyList()
    }
}