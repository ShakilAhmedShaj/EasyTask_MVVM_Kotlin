package com.shajt3ch.todomvvm.model.repository

import com.shajt3ch.todomvvm.model.local.db.AppDatabase
import com.shajt3ch.todomvvm.model.local.entity.TaskEntity
import com.shajt3ch.todomvvm.model.remote.NetworkService
import com.shajt3ch.todomvvm.model.remote.request.todo.DeleteTaskRequest

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