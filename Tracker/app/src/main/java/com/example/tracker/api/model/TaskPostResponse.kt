package com.example.tracker.api.model

import com.google.gson.annotations.SerializedName

data class TaskPostResponse (
    @SerializedName("message")
    var message: String
)