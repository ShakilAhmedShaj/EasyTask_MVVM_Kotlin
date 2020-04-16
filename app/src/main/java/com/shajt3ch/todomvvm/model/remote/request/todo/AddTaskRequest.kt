package com.shajt3ch.todomvvm.model.remote.request.todo


import com.google.gson.annotations.SerializedName

data class AddTaskRequest(
    @SerializedName("user_id")
    val userId: String, // 1
    @SerializedName("title")
    val title: String, // Title 1
    @SerializedName("body")
    val body: String, // Body 1
    @SerializedName("status")
    val status: String // PENDING
)