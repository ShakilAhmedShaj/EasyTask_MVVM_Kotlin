package com.shajt3ch.todomvvm.viewmodel.task

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.shajt3ch.todomvvm.BuildConfig
import com.shajt3ch.todomvvm.model.local.AppPreferences
import retrofit2.HttpException

class TaskDetailViewModel(application: Application) : AndroidViewModel(application) {
    companion object {
        const val TAG = "TaskDetailViewModel"
    }

    private var  sharedPreferences = application.getSharedPreferences(BuildConfig.PREF_NAME, Context.MODE_PRIVATE)
    private  var appPreferences: AppPreferences
    private  var userId: String

    val id: MutableLiveData<String> = MutableLiveData()
    val dataTime: MutableLiveData<String> = MutableLiveData()
    val title: MutableLiveData<String> = MutableLiveData()
    val body: MutableLiveData<String> = MutableLiveData()
    val status: MutableLiveData<String> = MutableLiveData()
    val userIdField: MutableLiveData<String> = MutableLiveData()
    val bgColor: MutableLiveData<String> = MutableLiveData()
    val isEditable: MutableLiveData<Boolean> = MutableLiveData()

    init {
        appPreferences = AppPreferences(sharedPreferences)
        userId = appPreferences.getUserId().toString()
    }


    fun checkUserId() {

        try {
            isEditable.value = userIdField.value == userId

        } catch (httpException: HttpException) {
            Log.e(TAG, httpException.toString())


        } catch (exception: Exception) {
            Log.e(TAG, exception.toString())

        }

    }

}
