package com.shajt3ch.todomvvm.viewmodel.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.shajt3ch.todomvvm.BuildConfig
import com.shajt3ch.todomvvm.model.remote.Networking
import com.shajt3ch.todomvvm.model.repository.ValidateTokenRepository

/**
 * Created by Shakil Ahmed Shaj on 15,April,2020.
 * shakilahmedshaj@gmail.com
 */
class SplashViewModel : ViewModel() {

    companion object {
        const val TAG = "SplashViewModel"
    }

    private val networkService = Networking.create(BuildConfig.BASE_URL)
    private val validateTokenRepository = ValidateTokenRepository(networkService)

    fun validateToken(token: String) = liveData {
        val data = validateTokenRepository.validateToken(token)
        emit(data)
    }

}