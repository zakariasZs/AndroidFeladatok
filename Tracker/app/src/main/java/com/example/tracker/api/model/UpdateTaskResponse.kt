package com.example.tracker.api.model

import com.google.gson.annotations.SerializedName

data class UpdateTaskResponse (
    @SerializedName("message")
    var message: String
)