package com.example.tracker.api.model

import com.google.gson.annotations.SerializedName

data class GetDepartmentResponse(

    @SerializedName("ID")
    var id: Int,

    @SerializedName("name")
    var department_name: String

)