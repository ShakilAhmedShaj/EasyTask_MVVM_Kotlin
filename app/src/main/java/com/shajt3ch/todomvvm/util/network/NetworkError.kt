package com.shajt3ch.todomvvm.util.network

import com.google.gson.annotations.SerializedName

/**
 * Created by Shakil Ahmed Shaj on 23,April,2020.
 * shakilahmedshaj@gmail.com
 */
data class NetworkError(
    @SerializedName("statusCode")
    val statusCode: Int = -1,
    @SerializedName("message")
    val message: String = "Something went wrong. Please try again."
) {

}