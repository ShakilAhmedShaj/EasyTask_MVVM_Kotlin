package com.decimalab.easytask.model.remote.request.task


import com.google.gson.annotations.SerializedName

data class AddTaskRequest(
    @SerializedName("user_id")
    val userId: Int, // 1
    @SerializedName("title")
    val title: String, // Title 1
    @SerializedName("body")
    val body: String, // Body 1
    @SerializedName("status")
    val status: String // PENDING
)