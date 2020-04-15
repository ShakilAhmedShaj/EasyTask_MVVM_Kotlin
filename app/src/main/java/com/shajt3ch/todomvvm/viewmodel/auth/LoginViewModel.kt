package com.shajt3ch.todomvvm.viewmodel.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.shajt3ch.todomvvm.BuildConfig
import com.shajt3ch.todomvvm.model.remote.Networking
import com.shajt3ch.todomvvm.model.remote.request.auth.LoginRequest
import com.shajt3ch.todomvvm.model.repository.LoginRepository
import kotlinx.coroutines.Dispatchers.IO

/**
 * Created by Shakil Ahmed Shaj on 14,April,2020.
 * shakilahmedshaj@gmail.com
 */
class LoginViewModel : ViewModel() {

    companion object {
        const val TAG = "LoginViewModel"
    }

    private val networkService = Networking.create(BuildConfig.BASE_URL)
    private val loginRepository = LoginRepository(networkService)


    fun login(loginRequest: LoginRequest) = liveData(IO) {

        val data = loginRepository.login(loginRequest)
        emit(data)

    }

}