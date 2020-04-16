package com.shajt3ch.todomvvm.viewmodel.todo

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.shajt3ch.todomvvm.BuildConfig
import com.shajt3ch.todomvvm.model.local.AppPreferences
import com.shajt3ch.todomvvm.model.remote.Networking
import com.shajt3ch.todomvvm.model.remote.request.todo.AddTaskRequest
import com.shajt3ch.todomvvm.model.repository.AddTaskRepository

class TaskViewModel : ViewModel() {

    companion object {
        const val TAG = "TaskViewModel"
    }

    private val networkService = Networking.create(BuildConfig.BASE_URL)
    private lateinit var addTaskRepository: AddTaskRepository

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var appPreferences: AppPreferences
    private var token: String = ""

    fun init(context: Context) {
        sharedPreferences =
            context.getSharedPreferences("com.shajt3ch.todomvvm.pref", Context.MODE_PRIVATE)
        appPreferences = AppPreferences(sharedPreferences)

        token = appPreferences.getAccessToken().toString()

    }


    fun addTask(addTaskRequest: AddTaskRequest) = liveData {
        val data = addTaskRepository.addTask(token, addTaskRequest)
        emit(data)
    }


}
