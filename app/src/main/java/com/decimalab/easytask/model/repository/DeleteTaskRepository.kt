package com.decimalab.easytask.model.repository

import com.decimalab.easytask.model.local.db.AppDatabase
import com.decimalab.easytask.model.local.entity.TaskEntity
import com.decimalab.easytask.model.remote.NetworkService
import com.decimalab.easytask.model.remote.request.task.DeleteTaskRequest

/**
 * Created by Shakil Ahmed Shaj on 30,April,2020.
 * shakilahmedshaj@gmail.com
 */
class DeleteTaskRepository(
    private val networkService: NetworkService,
    private val appDatabase: AppDatabase
) {

    suspend fun deleteTaskFromApi(token: String, deleteTaskRequest: DeleteTaskRequest) =
        networkService.deleteTask(token, deleteTaskRequest)

    suspend fun deleteTaskFromDb(taskEntity: TaskEntity) = appDatabase.taskDao().delete(taskEntity)

    suspend fun deleteFromDb(taskId: String) = appDatabase.taskDao().deleteTaskFromDb(taskId)
}