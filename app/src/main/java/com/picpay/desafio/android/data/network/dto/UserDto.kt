package com.picpay.desafio.android.data.network.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

@Parcelize
data class UserDto(
    @SerializedName("img") var img: String,
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Int,
    @SerializedName("username") val username: String
) : Parcelable {

    fun toJsonString(): String {

        val json = JSONObject()
        json.put("img", img)
        json.put("name", name)
        json.put("id", id)
        json.put("username", username)

        return json.toString()
    }
}