package com.shajt3ch.easytask.model.remote.request.auth

import com.google.gson.annotations.SerializedName

/**
 * Created by Shakil Ahmed Shaj on 14,April,2020.
 * shakilahmedshaj@gmail.com
 */
data class RegisterRequest(
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("password_confirmation")
    val password_confirmation: String
) {
}