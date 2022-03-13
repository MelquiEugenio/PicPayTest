package com.picpay.desafio.android

import com.picpay.desafio.android.model.network.api.PicPayService
import com.picpay.desafio.android.model.network.dto.UserDto

class ExampleService(
    private val service: PicPayService
) {

    fun example(): List<UserDto> {
        val users = service.getUsers().execute()

        return users.body() ?: emptyList()
    }
}