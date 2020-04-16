package com.shajt3ch.todomvvm.model.repository

import com.shajt3ch.todomvvm.model.remote.NetworkService

/**
 * Created by Shakil Ahmed Shaj on 16,April,2020.
 * shakilahmedshaj@gmail.com
 */
class TaskRepository(private val networkService: NetworkService) {

    suspend fun getAllTask(token: String) = networkService.getAllTask(token)


}