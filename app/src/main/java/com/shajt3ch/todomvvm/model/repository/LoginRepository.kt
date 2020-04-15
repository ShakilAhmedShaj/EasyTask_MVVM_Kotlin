package com.shajt3ch.todomvvm.model.repository

import com.shajt3ch.todomvvm.model.remote.NetworkService
import com.shajt3ch.todomvvm.model.remote.request.auth.LoginRequest

/**
 * Created by Shakil Ahmed Shaj on 14,April,2020.
 * shakilahmedshaj@gmail.com
 */
class LoginRepository(private val networkService: NetworkService) {

    suspend fun login(loginRequest: LoginRequest) = networkService.login(loginRequest)
}