package com.shajt3ch.todomvvm.viewmodel.home

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.shajt3ch.todomvvm.BuildConfig
import com.shajt3ch.todomvvm.model.local.AppPreferences
import com.shajt3ch.todomvvm.model.remote.Networking
import com.shajt3ch.todomvvm.model.remote.response.todo.TaskResponse
import com.shajt3ch.todomvvm.model.repository.AddTaskRepository
import com.shajt3ch.todomvvm.model.repository.TaskRepository

class HomeViewModel : ViewModel() {

    companion object {
        const val TAG = "HomeViewModel"
    }

    private val networkService = Networking.create(BuildConfig.BASE_URL)
    private lateinit var taskRepository: TaskRepository

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var appPreferences: AppPreferences

    private lateinit var token: String
    val taskList: MutableLiveData<List<TaskResponse>> = MutableLiveData()


    fun init(context: Context) {
        taskRepository = TaskRepository(networkService)

        sharedPreferences =
            context.getSharedPreferences("com.shajt3ch.todomvvm.pref", Context.MODE_PRIVATE)
        appPreferences = AppPreferences(sharedPreferences)

        token = appPreferences.getAccessToken().toString()


    }


    fun getAllTask() = liveData {

        val data = taskRepository.getAllTask(token)

        if (data.code() == 200) {
            taskList.postValue(data.body())
        }



        emit(data)

    }

}
