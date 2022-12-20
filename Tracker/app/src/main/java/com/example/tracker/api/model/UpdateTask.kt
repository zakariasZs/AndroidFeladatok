package com.example.tracker.api.model

import com.google.gson.annotations.SerializedName


data class UpdateTask (

    @SerializedName("taskId")
    var taskId: Int,

    @SerializedName("title")
    var title: String,

    @SerializedName("description")
    var description: String,

    @SerializedName("assigneeToUserId")
    var assigneeToUserId: Int,

    @SerializedName("priority")
    var priority: Int,

    @SerializedName("deadline")
    var deadline: Long,

    @SerializedName("departmentId")
    var departmentId: Int,

    @SerializedName("status")
    var status: Int

)