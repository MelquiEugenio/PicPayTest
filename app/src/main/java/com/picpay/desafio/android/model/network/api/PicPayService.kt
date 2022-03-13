package com.picpay.desafio.android.model.network.api

import com.picpay.desafio.android.model.network.dto.UserDto
import retrofit2.Call
import retrofit2.http.GET


interface PicPayService {

    @GET("users")
    fun getUsers(): Call<List<UserDto>>
}