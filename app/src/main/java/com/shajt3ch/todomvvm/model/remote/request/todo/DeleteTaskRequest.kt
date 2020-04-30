package com.shajt3ch.todomvvm.model.remote.request.todo

import com.google.gson.annotations.SerializedName

/**
 * Created by Shakil Ahmed Shaj on 30,April,2020.
 * shakilahmedshaj@gmail.com
 */
data class DeleteTaskRequest(
    @SerializedName("id")
    val id: String,
    @SerializedName("user_id")
    val user_id: String
) {
}