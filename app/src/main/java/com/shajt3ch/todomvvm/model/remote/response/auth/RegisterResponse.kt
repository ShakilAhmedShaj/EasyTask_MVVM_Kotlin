package com.shajt3ch.todomvvm.model.remote.response.auth


import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("name")
    val user_name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("id")
    val id: Int
)