package com.shajt3ch.todomvvm.model.repository

import com.shajt3ch.todomvvm.model.remote.NetworkService
import com.shajt3ch.todomvvm.model.remote.request.todo.EditTaskRequest
import com.shajt3ch.todomvvm.model.remote.response.todo.EditTaskResponse
import retrofit2.Response

/**
 * Created by Shakil Ahmed Shaj on 18,April,2020.
 * shakilahmedshaj@gmail.com
 */
class EditTaskRepository(private val networkService: NetworkService) {

    suspend fun editTask(
        token: String,
        editTaskRequest: EditTaskRequest
    ): Response<EditTaskResponse> =
        networkService.editTask(token, editTaskRequest)
}