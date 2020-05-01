package com.shajt3ch.easytask.viewmodel.task

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.shajt3ch.easytask.BuildConfig
import com.shajt3ch.easytask.model.local.AppPreferences
import com.shajt3ch.easytask.model.remote.Networking
import com.shajt3ch.easytask.model.remote.request.task.AddTaskRequest
import com.shajt3ch.easytask.model.repository.AddTaskRepository
import retrofit2.HttpException

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    companion object {
        const val TAG = "TaskViewModel"
    }

    private val networkService = Networking.create(BuildConfig.BASE_URL)
    private var addTaskRepository: AddTaskRepository
    private var  sharedPreferences = application.getSharedPreferences(BuildConfig.PREF_NAME, Context.MODE_PRIVATE)
    private var appPreferences: AppPreferences

    val userId: MutableLiveData<Int> = MutableLiveData()
    val isError: MutableLiveData<String> = MutableLiveData()
    val progress: MutableLiveData<Boolean> = MutableLiveData()
    private val isSuccess: MutableLiveData<Boolean> = MutableLiveData()

    private var token: String = ""

    init {
        addTaskRepository = AddTaskRepository(networkService)
        appPreferences = AppPreferences(sharedPreferences)
        token = appPreferences.getAccessToken().toString()
        userId.value = appPreferences.getUserId()
    }


    fun addTask(addTaskRequest: AddTaskRequest) = liveData {

        try {
            progress.value = true

            val data = addTaskRepository.addTask(token, addTaskRequest)
            isSuccess.value = data.code() == 201
            emit(isSuccess.value)

            progress.value = false

        } catch (httpException: HttpException) {
            Log.e(TAG, httpException.toString())
            isError.value = httpException.toString()


        } catch (exception: Exception) {
            Log.e(TAG, exception.toString())
            isError.value = exception.toString()

        }
    }
}
