package com.shajt3ch.todomvvm.viewmodel.splash

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.shajt3ch.todomvvm.BuildConfig
import com.shajt3ch.todomvvm.model.local.AppPreferences
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

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var appPreferences: AppPreferences

    private lateinit var token: String

    fun init(context: Context) {
        sharedPreferences =
            context.getSharedPreferences("com.shajt3ch.todomvvm.pref", Context.MODE_PRIVATE)
        appPreferences = AppPreferences(sharedPreferences)
        token = appPreferences.getAccessToken().toString()
        //token = "Bearer ${appPreferences.getAccessToken()}"


    }


    fun validateToken() = liveData {
        val data = validateTokenRepository.validateToken(token)
        emit(data)
    }

}