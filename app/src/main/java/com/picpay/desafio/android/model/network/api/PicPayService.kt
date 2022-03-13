package com.picpay.desafio.android.model.network.api

import com.picpay.desafio.android.model.network.dto.User
import retrofit2.Call
import retrofit2.http.GET


interface PicPayService {

    @GET("users")
    suspend fun getUsers(): List<User>
}