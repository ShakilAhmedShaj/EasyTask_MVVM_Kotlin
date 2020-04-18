package com.shajt3ch.todomvvm.model.repository

import com.shajt3ch.todomvvm.model.remote.NetworkService
import com.shajt3ch.todomvvm.model.remote.request.todo.AddTaskRequest

/**
 * Created by Shakil Ahmed Shaj on 16,April,2020.
 * shakilahmedshaj@gmail.com
 */
class AddTaskRepository(private val networkService: NetworkService) {

    suspend fun addTask(token: String, addTaskRequest: AddTaskRequest) =
        networkService.addTask(token, addTaskRequest)
}