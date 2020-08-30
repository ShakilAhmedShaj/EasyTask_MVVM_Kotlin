package com.decimalab.easytask.model.repository

import com.decimalab.easytask.model.remote.NetworkService
import com.decimalab.easytask.model.remote.request.auth.RegisterRequest

/**
 * Created by Shakil Ahmed Shaj on 14,April,2020.
 * shakilahmedshaj@gmail.com
 */
class RegisterRepository(private val networkService: NetworkService) {

    suspend fun register(registerRequest: RegisterRequest) =
        networkService.register(registerRequest)


}