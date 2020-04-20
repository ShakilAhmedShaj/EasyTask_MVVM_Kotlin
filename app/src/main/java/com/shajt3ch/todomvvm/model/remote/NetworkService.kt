package com.shajt3ch.todomvvm.model.remote

import com.shajt3ch.todomvvm.model.remote.request.auth.LoginRequest
import com.shajt3ch.todomvvm.model.remote.request.auth.RegisterRequest
import com.shajt3ch.todomvvm.model.remote.request.todo.AddTaskRequest
import com.shajt3ch.todomvvm.model.remote.request.todo.EditTaskRequest
import com.shajt3ch.todomvvm.model.remote.response.auth.LoginResponse
import com.shajt3ch.todomvvm.model.remote.response.auth.RegisterResponse
import com.shajt3ch.todomvvm.model.remote.response.auth.ValidateResponse
import com.shajt3ch.todomvvm.model.remote.response.profile.UserProfileResponse
import com.shajt3ch.todomvvm.model.remote.response.todo.AddTaskResponse
import com.shajt3ch.todomvvm.model.remote.response.todo.EditTaskResponse
import com.shajt3ch.todomvvm.model.remote.response.todo.TaskResponse
import retrofit2.Response
import retrofit2.http.*

/**
 * Created by Shakil Ahmed Shaj on 14,April,2020.
 * shakilahmedshaj@gmail.com
 */
interface NetworkService {

    @POST(EndPoints.REGISTER)
    suspend fun register(@Body registerRequest: RegisterRequest): Response<RegisterResponse>

    @POST(EndPoints.LOGIN)
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @GET(EndPoints.VALIDATE_TOKEN)
    suspend fun validateToken(@Header("Authorization") token: String): Response<ValidateResponse>

    @POST(EndPoints.ADD_TASK)
    suspend fun addTask(
        @Header("Authorization") token: String,
        @Body addTaskRequest: AddTaskRequest
    ): Response<AddTaskResponse>

    @GET(EndPoints.ALL_TASK)
    suspend fun getAllTask(@Header("Authorization") token: String): Response<List<TaskResponse>>


    @POST(EndPoints.EDIT_TASK)
    suspend fun editTask(
        @Header("Authorization") token: String,
        @Body editTaskRequest: EditTaskRequest
    ): Response<EditTaskResponse>

    @GET("${EndPoints.GET_USER_PROFILE} {id}")
    suspend fun getUserProfile(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Response<UserProfileResponse>

}