package com.decimalab.easytask.viewmodel.task

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.decimalab.easytask.BuildConfig
import com.decimalab.easytask.model.local.AppPreferences
import com.decimalab.easytask.model.local.db.AppDatabase
import com.decimalab.easytask.model.local.entity.TaskEntity
import com.decimalab.easytask.model.remote.Networking
import com.decimalab.easytask.model.remote.request.task.EditTaskRequest
import com.decimalab.easytask.model.remote.response.task.EditTaskResponse
import com.decimalab.easytask.model.repository.EditTaskRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class EditTaskViewModel(application: Application) : AndroidViewModel(application) {

    companion object {
        const val TAG = "EditTaskViewModel"
    }

    private val networkService = Networking.create(BuildConfig.BASE_URL)
    private var editTaskRepository: EditTaskRepository
    private var sharedPreferences = application.getSharedPreferences(BuildConfig.PREF_NAME, Context.MODE_PRIVATE)
    private var appPreferences: AppPreferences
    private var token: String = ""


    val id: MutableLiveData<String> = MutableLiveData()
    val taskId: MutableLiveData<String> = MutableLiveData()
    val title: MutableLiveData<String> = MutableLiveData()
    val body: MutableLiveData<String> = MutableLiveData()
    val status: MutableLiveData<String> = MutableLiveData()
    val index: MutableLiveData<Int> = MutableLiveData()
    val taskList: ArrayList<String> = ArrayList()
    val userId: MutableLiveData<Int> = MutableLiveData()
    val loading: MutableLiveData<Boolean> = MutableLiveData()
    val isSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val isError: MutableLiveData<String> = MutableLiveData()

    init {
        editTaskRepository =
            EditTaskRepository(networkService, AppDatabase.getInstance(application))
        appPreferences = AppPreferences(sharedPreferences)
        token = appPreferences.getAccessToken().toString()
        userId.value = appPreferences.getUserId()
    }

    fun getIndexFromTaskList() {

        index.value = taskList.indexOf(status.value)
    }

    fun editTask() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                loading.postValue(true)
                val data = editTaskRepository.editTask(
                    token, EditTaskRequest(
                        taskId.value!!.toInt(),
                        userId.value.toString(),
                        title.value.toString(),
                        body.value.toString(),
                        status.value.toString()
                    )
                )
                if (data.code() == 201) {

                    /*
                    updating to local db
                     */
                    updateTask(data.body()!!)
                    isSuccess.postValue(true)
                } else {
                    isSuccess.postValue(false)
                }

                loading.postValue(false)

            } catch (httpException: HttpException) {
                Log.e(TAG, httpException.toString())
                isError.postValue(httpException.toString())

            } catch (exception: Exception) {
                Log.e(TAG, exception.toString())
                isError.postValue(exception.toString())
            }

        }

    }

    /*
    update in local db
     */

    private fun updateTask(editTaskResponse: EditTaskResponse) {
        try {

            CoroutineScope(Dispatchers.IO).launch {

                loading.postValue(true)

                val id = editTaskRepository.updateTask(
                    TaskEntity(
                        id = id.value!!.toLong(),
                        taskId = editTaskResponse.id,
                        title = editTaskResponse.title,
                        body = editTaskResponse.body,
                        status = editTaskResponse.status,
                        userId = editTaskResponse.userId.toInt(),
                        createdAt = editTaskResponse.createdAt,
                        updatedAt = editTaskResponse.updatedAt
                    )
                )

                if (id > 0) {
                    Log.e(TAG, " $id : Update Success")
                }
                loading.postValue(false)
            }


        } catch (error: Exception) {
            Log.e(TAG, error.message.toString())
        }

    }

}
