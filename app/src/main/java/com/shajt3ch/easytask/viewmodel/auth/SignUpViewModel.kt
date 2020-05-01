package com.shajt3ch.easytask.viewmodel.auth

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shajt3ch.easytask.BuildConfig
import com.shajt3ch.easytask.model.remote.Networking
import com.shajt3ch.easytask.model.remote.request.auth.RegisterRequest
import com.shajt3ch.easytask.model.repository.RegisterRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException

/**
 * Created by Shakil Ahmed Shaj on 14,April,2020.
 * shakilahmedshaj@gmail.com
 */
class SignUpViewModel : ViewModel() {

    companion object {
        const val TAG = "RegisterViewModel"
    }

    private val networkService = Networking.create(BuildConfig.BASE_URL)
    private val registerRepository = RegisterRepository(networkService)
    val isSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val isError: MutableLiveData<String> = MutableLiveData()
    val loading: MutableLiveData<Boolean> = MutableLiveData()

    fun register(registerRequest: RegisterRequest) {
        loading.value = true
        viewModelScope.launch {
            try {
                val data = registerRepository.register(registerRequest)
                isSuccess.postValue(data.code() == 201)

                loading.postValue(false)

            } catch (httpException: HttpException) {
                Log.e(TAG, httpException.toString())
                isError.postValue(httpException.toString())
                loading.postValue(false)

            } catch (exception: Exception) {
                Log.e(TAG, exception.toString())
                isError.postValue(exception.toString())
                loading.postValue(false)
            }

        }
    }

}