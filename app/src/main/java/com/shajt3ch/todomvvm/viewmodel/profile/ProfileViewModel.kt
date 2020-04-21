package com.shajt3ch.todomvvm.viewmodel.profile

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.shajt3ch.todomvvm.BuildConfig
import com.shajt3ch.todomvvm.model.local.AppPreferences
import com.shajt3ch.todomvvm.model.remote.Networking
import com.shajt3ch.todomvvm.model.remote.response.profile.UserProfileResponse
import com.shajt3ch.todomvvm.model.repository.UserProfileRepository
import retrofit2.HttpException

class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    companion object {
        const val TAG = "ProfileViewModel"
    }

    private val networkService = Networking.create(BuildConfig.BASE_URL)
    private lateinit var userProfileRepository: UserProfileRepository

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var appPreferences: AppPreferences

    private lateinit var token: String
    private lateinit var userId: String
    val loading: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var profile: UserProfileResponse
    val imageUrl: MutableLiveData<String> = MutableLiveData()

    init {
        userProfileRepository = UserProfileRepository(networkService)
        sharedPreferences = application.getSharedPreferences("com.shajt3ch.todomvvm.pref", Context.MODE_PRIVATE)
        appPreferences = AppPreferences(sharedPreferences)

        token = appPreferences.getAccessToken().toString()
        userId = appPreferences.getUserId().toString()
    }

    fun getUserProfile() = liveData {

        loading.postValue(true)
        val data = userProfileRepository.getUserProfile(token, userId)
        if (data.code() == 200) {
            profile = data.body()!!

            imageUrl.postValue(profile.profileImage)
        }

        emit(profile)
        loading.postValue(false)


    }

}
