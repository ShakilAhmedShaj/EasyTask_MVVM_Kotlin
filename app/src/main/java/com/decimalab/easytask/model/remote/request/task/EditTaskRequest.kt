package com.decimalab.easytask.model.remote.request.task


import com.google.gson.annotations.SerializedName

data class EditTaskRequest(
    @SerializedName("id")
    val id: Int, // 26
    @SerializedName("user_id")
    val userId: String, // 1
    @SerializedName("title")
    val title: String, // Title Update
    @SerializedName("body")
    val body: String, // Body 1
    @SerializedName("status")
    val status: String // PENDING
)