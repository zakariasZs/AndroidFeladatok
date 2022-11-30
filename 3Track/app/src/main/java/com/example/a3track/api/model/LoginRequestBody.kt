package com.example.a3track.api.model

import com.google.gson.annotations.SerializedName


data class LoginRequestBody(
    @SerializedName("username")
    var username: String,

    @SerializedName("password")
    var password: String
)