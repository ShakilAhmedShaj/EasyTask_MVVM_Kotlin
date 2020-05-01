package com.shajt3ch.easytask.model.remote.response.auth

import com.google.gson.annotations.SerializedName

/**
 * Created by Shakil Ahmed Shaj on 15,April,2020.
 * shakilahmedshaj@gmail.com
 */
data class ValidateResponse(
    @SerializedName("message")
    val message: String
)