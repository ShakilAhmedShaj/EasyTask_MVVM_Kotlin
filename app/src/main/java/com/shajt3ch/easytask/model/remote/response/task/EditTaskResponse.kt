package com.shajt3ch.easytask.model.remote.response.task


import com.google.gson.annotations.SerializedName

data class EditTaskResponse(
    @SerializedName("id")
    val id: Int, // 26
    @SerializedName("user_id")
    val userId: String, // 1
    @SerializedName("title")
    val title: String, // Title Update
    @SerializedName("body")
    val body: String, // Body 1
    @SerializedName("status")
    val status: String, // PENDING
    @SerializedName("created_at")
    val createdAt: String, // 2020-04-16T20:50:00.000000Z
    @SerializedName("updated_at")
    val updatedAt: String // 2020-04-18T12:08:09.000000Z

)