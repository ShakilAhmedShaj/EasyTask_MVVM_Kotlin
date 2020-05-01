package com.shajt3ch.easytask.model.remote.response.task


import com.google.gson.annotations.SerializedName

data class AddTaskResponse(
    @SerializedName("user_id")
    val userId: String, // 1
    @SerializedName("title")
    val title: String, // Title 1
    @SerializedName("body")
    val body: String, // Body 1
    @SerializedName("status")
    val status: String, // PENDING
    @SerializedName("updated_at")
    val updatedAt: String, // 2020-04-16T11:37:09.000000Z
    @SerializedName("created_at")
    val createdAt: String, // 2020-04-16T11:37:09.000000Z
    @SerializedName("id")
    val id: Int // 2
)