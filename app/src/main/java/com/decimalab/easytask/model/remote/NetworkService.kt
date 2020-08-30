package com.decimalab.easytask.model.remote

import com.decimalab.easytask.model.remote.request.auth.LoginRequest
import com.decimalab.easytask.model.remote.request.auth.RegisterRequest
import com.decimalab.easytask.model.remote.request.task.AddTaskRequest
import com.decimalab.easytask.model.remote.request.task.DeleteTaskRequest
import com.decimalab.easytask.model.remote.request.task.EditTaskRequest
import com.decimalab.easytask.model.remote.response.auth.LoginResponse
import com.decimalab.easytask.model.remote.response.auth.RegisterResponse
import com.decimalab.easytask.model.remote.response.auth.ValidateResponse
import com.decimalab.easytask.model.remote.response.profile.EditProfileResponse
import com.decimalab.easytask.model.remote.response.profile.UserProfileResponse
import com.decimalab.easytask.model.remote.response.task.AddTaskResponse
import com.decimalab.easytask.model.remote.response.task.EditTaskResponse
import com.decimalab.easytask.model.remote.response.task.TaskResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
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

    @GET("${EndPoints.GET_TASK_By_ID} {maxId}")
    suspend fun getTaskById(
        @Header(
            "Authorization"
        ) token: String, @Path("maxId") maxId: String

    ): Response<List<TaskResponse>>

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

    @Multipart
    @POST(EndPoints.EDIT_PROFILE)
    suspend fun editProfile(
        @Header("Authorization") token: String,
        @Part("id") id: RequestBody,
        @Part("name") name: RequestBody,
        @Part("email") email: RequestBody,
        @Part profile_image: MultipartBody.Part? = null,
        @Part("bio") bio: RequestBody
    ): Response<EditProfileResponse>

    @POST(EndPoints.DELETE_TASK)
    suspend fun deleteTask(
        @Header("Authorization") token: String,
        @Body deleteTaskRequest: DeleteTaskRequest
    ): Response<TaskResponse>

}