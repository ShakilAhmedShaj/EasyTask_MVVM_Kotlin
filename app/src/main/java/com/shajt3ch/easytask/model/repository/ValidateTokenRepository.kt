package com.shajt3ch.easytask.model.repository

import com.shajt3ch.easytask.model.remote.NetworkService

/**
 * Created by Shakil Ahmed Shaj on 15,April,2020.
 * shakilahmedshaj@gmail.com
 */
class ValidateTokenRepository(private val networkService: NetworkService) {

    suspend fun validateToken(token: String) = networkService.validateToken(token)

}