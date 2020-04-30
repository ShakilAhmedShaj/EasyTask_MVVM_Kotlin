package com.shajt3ch.todomvvm.viewmodel.profile

import android.app.Application
import android.content.Context
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
    private  var userProfileRepository: UserProfileRepository
    private var  sharedPreferences = application.getSharedPreferences(BuildConfig.PREF_NAME, Context.MODE_PRIVATE)
    private  var appPreferences: AppPreferences
    private  var token: String
    private  var userId: String
    lateinit var profile: UserProfileResponse

    val imageUrl: MutableLiveData<String> = MutableLiveData()
    val loading: MutableLiveData<Boolean> = MutableLiveData()

    init {
        userProfileRepository = UserProfileRepository(networkService)
        appPreferences = AppPreferences(sharedPreferences)

        token = appPreferences.getAccessToken().toString()
        userId = appPreferences.getUserId().toString()
    }

    fun getUserProfile() = liveData {


        try {
            loading.postValue(true)
            val data = userProfileRepository.getUserProfile(token, userId)
            if (data.code() == 200) {
                profile = data.body()!!

                imageUrl.postValue(profile.profileImage)
            }

            emit(profile)
            loading.postValue(false)

        } catch (httpException: HttpException) {
            Log.e(TAG, httpException.toString())


        } catch (exception: Exception) {
            Log.e(TAG, exception.toString())

        }




    }

}
