package com.decimalab.easytask.model.repository

import com.decimalab.easytask.model.local.db.AppDatabase
import com.decimalab.easytask.model.local.entity.TaskEntity
import com.decimalab.easytask.model.remote.NetworkService
import com.decimalab.easytask.model.remote.request.task.EditTaskRequest
import com.decimalab.easytask.model.remote.response.task.EditTaskResponse
import retrofit2.Response

/**
 * Created by Shakil Ahmed Shaj on 18,April,2020.
 * shakilahmedshaj@gmail.com
 */
class EditTaskRepository(
    private val networkService: NetworkService,
    private val appDatabase: AppDatabase
) {

    suspend fun editTask(
        token: String,
        editTaskRequest: EditTaskRequest
    ): Response<EditTaskResponse> =
        networkService.editTask(token, editTaskRequest)

    suspend fun updateTask(taskEntity: TaskEntity) = appDatabase.taskDao().update(taskEntity)
}