package com.shajt3ch.todomvvm.viewmodel.home

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.shajt3ch.todomvvm.BuildConfig
import com.shajt3ch.todomvvm.model.local.AppPreferences
import com.shajt3ch.todomvvm.model.local.db.AppDatabase
import com.shajt3ch.todomvvm.model.local.entity.TaskEntity
import com.shajt3ch.todomvvm.model.remote.Networking
import com.shajt3ch.todomvvm.model.remote.response.todo.TaskResponse
import com.shajt3ch.todomvvm.model.repository.TaskRepository
import com.shajt3ch.todomvvm.util.network.NetworkHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    companion object {
        const val TAG = "HomeViewModel"
    }

    private val networkService = Networking.create(BuildConfig.BASE_URL)
    private var taskRepository: TaskRepository
    private var sharedPreferences =
        application.getSharedPreferences(BuildConfig.PREF_NAME, Context.MODE_PRIVATE)
    private var appPreferences: AppPreferences
    private var token: String
    val taskList: MutableLiveData<List<TaskResponse>> = MutableLiveData()
    val taskListFromDb: MutableLiveData<List<TaskEntity>> = MutableLiveData()
    val progress: MutableLiveData<Boolean> = MutableLiveData()
    val isError: MutableLiveData<String> = MutableLiveData()
    val maxRecId: MutableLiveData<String> = MutableLiveData()
    private var context: Context? = null

    init {
        taskRepository = TaskRepository(networkService, AppDatabase.getInstance(application))
        appPreferences = AppPreferences(sharedPreferences)
        token = appPreferences.getAccessToken().toString()
        context = application

        getMaxIdFromDb()
    }


    fun getAllTask() = liveData {

        try {
            progress.postValue(true)

            val data = taskRepository.getAllTask(token)

            if (data.code() == 200) {
                taskList.value = data.body()

                for (task in taskList.value!!) {

                    val id = taskRepository.insert(
                        TaskEntity(
                            taskId = task.id,
                            title = task.title,
                            body = task.body,
                            status = task.status,
                            userId = task.userId,
                            bg_color = task.bg_color
                        )
                    )
                    Log.d(TAG, "New Record : $id")
                }

            }
            emit(taskList.value)
            progress.postValue(false)

        } catch (httpException: HttpException) {
            Log.e(TAG, httpException.toString())
            isError.value = httpException.toString()


        } catch (exception: Exception) {
            Log.e(TAG, exception.toString())
            isError.value = exception.toString()

        }

    }

    private fun getMaxIdFromDb() {
        CoroutineScope(Dispatchers.IO).launch {

            try {

                var maxId = taskRepository.getMaxTaskId()

                if (maxId == null) {
                    maxId = 0
                }

                // getTaskById(maxId.toString())


                maxRecId.postValue(maxId.toString())


            } catch (error: Exception) {
                Log.e(TAG, error.message.toString())
            }

        }
    }

    private fun getTaskById(maxId: String) {
        try {

            if (NetworkHelper.isNetworkConnected(context!!)) {

                CoroutineScope(Dispatchers.IO).launch {
                    progress.postValue(true)
                    val data = taskRepository.getTaskById(token, maxId)

                    if (data.code() == 200) {
                        taskList.value = data.body()

                        for (task in taskList.value!!) {

                            val id = taskRepository.insert(
                                TaskEntity(
                                    taskId = task.id,
                                    title = task.title,
                                    body = task.body,
                                    status = task.status,
                                    userId = task.userId,
                                    bg_color = task.bg_color
                                )
                            )
                            Log.d(TAG, "New Record : $id")
                        }

                        getTaskFromDb()

                    }
                    progress.postValue(false)
                }
            } else {
                getTaskFromDb()
            }

        } catch (error: Exception) {
            Log.e(TAG, error.message.toString())
        }
    }

    private fun getTaskFromDb() {
        try {
            CoroutineScope(Dispatchers.IO).launch {

                val data = taskRepository.getAllTaskFromDb()
                taskListFromDb.postValue(data)

            }


        } catch (error: Exception) {
            Log.e(TAG, error.message.toString())
        }
    }

}
