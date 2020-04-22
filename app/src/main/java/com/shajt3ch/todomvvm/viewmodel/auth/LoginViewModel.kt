package com.shajt3ch.todomvvm.viewmodel.auth

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.shajt3ch.todomvvm.BuildConfig
import com.shajt3ch.todomvvm.model.local.AppPreferences
import com.shajt3ch.todomvvm.model.remote.Networking
import com.shajt3ch.todomvvm.model.remote.request.auth.LoginRequest
import com.shajt3ch.todomvvm.model.remote.response.auth.LoginResponse
import com.shajt3ch.todomvvm.model.repository.LoginRepository
import kotlinx.coroutines.Dispatchers.IO
import retrofit2.HttpException
import kotlin.Exception

/**
 * Created by Shakil Ahmed Shaj on 14,April,2020.
 * shakilahmedshaj@gmail.com
 */
class LoginViewModel(application: Application) : AndroidViewModel(application) {

    companion object {
        const val TAG = "LoginViewModel"
    }

    private val networkService = Networking.create(BuildConfig.BASE_URL)
    private var loginRepository: LoginRepository
    private var sharedPreferences: SharedPreferences =
        application.getSharedPreferences("com.shajt3ch.todomvvm.pref", Context.MODE_PRIVATE)
    private var appPreferences: AppPreferences
    val loginResponse: MutableLiveData<LoginResponse> = MutableLiveData()
    val isSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val isError: MutableLiveData<String> = MutableLiveData()

    init {
        appPreferences = AppPreferences(sharedPreferences)
        loginRepository = LoginRepository(networkService, appPreferences)
    }

    fun login(loginRequest: LoginRequest) = liveData(IO) {

        try {
            val data = loginRepository.login(loginRequest)
            if (data.code() == 200) {
                loginResponse.postValue(data.body())
                isSuccess.postValue(true)
                emit(loginResponse.value)
            } else {
                isSuccess.postValue(false)
            }
        } catch (httpException: HttpException) {
            Log.e(TAG, httpException.toString())
            isError.postValue(httpException.toString())

        } catch (exception: Exception) {
            Log.e(TAG, exception.toString())
            isError.postValue(exception.toString())
        }

    }

    fun saveUserDetail(loginResponse: LoginResponse) = liveData(IO) {
        val dataScope = loginRepository.saveUserDetail(loginResponse)
        emit(dataScope)
    }

}