package com.example.tracker.api.model

import com.google.gson.annotations.SerializedName

data class UpdateProfileResponse(
    @SerializedName("message")
    var message: String
)