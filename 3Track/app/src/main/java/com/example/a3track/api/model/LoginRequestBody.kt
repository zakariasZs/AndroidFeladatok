package com.example.a3track.api.model

import com.google.gson.annotations.SerializedName


data class LoginRequestBody(
    @SerializedName("passwordKey")
    var passwordKey: String,

    @SerializedName("email")
    var email: String
)