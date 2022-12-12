package com.example.tracker.api.model

import com.google.gson.annotations.SerializedName

data class MyGroupsResponse(
    @SerializedName("ID")
    var id: Int,

    @SerializedName("name")
    var name: String

)