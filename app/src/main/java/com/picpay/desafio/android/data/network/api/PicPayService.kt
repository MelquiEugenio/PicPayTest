package com.picpay.desafio.android.data.network.api

import com.picpay.desafio.android.data.network.dto.UserDto
import retrofit2.http.GET


interface PicPayService {

    @GET("users")
    suspend fun getUsersFromApi(): List<UserDto>
}