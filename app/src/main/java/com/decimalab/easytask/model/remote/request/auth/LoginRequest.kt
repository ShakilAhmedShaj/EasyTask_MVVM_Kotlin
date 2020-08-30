package com.decimalab.easytask.model.remote.request.auth

import com.google.gson.annotations.SerializedName

/**
 * Created by Shakil Ahmed Shaj on 14,April,2020.
 * shakilahmedshaj@gmail.com
 */
data class LoginRequest(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
) {
}