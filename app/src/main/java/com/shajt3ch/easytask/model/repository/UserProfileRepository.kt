package com.shajt3ch.easytask.model.repository

import com.shajt3ch.easytask.model.remote.NetworkService
import com.shajt3ch.easytask.model.remote.response.profile.EditProfileResponse
import com.shajt3ch.easytask.model.remote.response.profile.UserProfileResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response

/**
 * Created by Shakil Ahmed Shaj on 19,April,2020.
 * shakilahmedshaj@gmail.com
 */
class UserProfileRepository(private val networkService: NetworkService) {

    suspend fun getUserProfile(token: String, id: String): Response<UserProfileResponse> = networkService.getUserProfile(token, id)

    suspend fun editProfile(
        token: String,
        id: RequestBody,
        name: RequestBody,
        email: RequestBody,
        profile_image: MultipartBody.Part? = null,
        bio: RequestBody
    ): Response<EditProfileResponse> = networkService.editProfile(
        token,
        id,
        name,
        email,
        profile_image,
        bio
    )
}