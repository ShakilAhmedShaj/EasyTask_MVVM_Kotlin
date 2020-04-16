package com.shajt3ch.todomvvm.model.remote.response.todo


import com.google.gson.annotations.SerializedName

data class TaskResponse(
    @SerializedName("id")
    val id: Int, // 15
    @SerializedName("user_id")
    val userId: Int, // 1
    @SerializedName("title")
    val title: String, // Task 1
    @SerializedName("body")
    val body: String, // Body 1
    @SerializedName("status")
    val status: String, // PENDING
    @SerializedName("created_at")
    val createdAt: String, // 2020-04-16 15:25:13
    @SerializedName("updated_at")
    val updatedAt: String // 2020-04-16 15:25:13
)