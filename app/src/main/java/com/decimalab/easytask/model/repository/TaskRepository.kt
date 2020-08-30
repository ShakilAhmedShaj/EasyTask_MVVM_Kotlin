package com.decimalab.easytask.model.repository

import com.decimalab.easytask.model.local.db.AppDatabase
import com.decimalab.easytask.model.local.entity.TaskEntity
import com.decimalab.easytask.model.remote.NetworkService

/**
 * Created by Shakil Ahmed Shaj on 16,April,2020.
 * shakilahmedshaj@gmail.com
 */
class TaskRepository(
    private val networkService: NetworkService,
    private val appDatabase: AppDatabase
) {

    suspend fun getAllTask(token: String) = networkService.getAllTask(token)
    suspend fun getTaskById(token: String, maxId: String) = networkService.getTaskById(token, maxId)

    suspend fun insert(taskEntity: TaskEntity) = appDatabase.taskDao().insert(taskEntity)
    suspend fun update(taskEntity: TaskEntity) = appDatabase.taskDao().update(taskEntity)
    suspend fun delete(taskEntity: TaskEntity) = appDatabase.taskDao().delete(taskEntity)

    suspend fun getAllTaskFromDb() = appDatabase.taskDao().getAllTaskFromDb()
    suspend fun getMaxTaskId() = appDatabase.taskDao().getMaxTaskId()


}