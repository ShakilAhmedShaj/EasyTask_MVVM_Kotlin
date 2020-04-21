package com.shajt3ch.todomvvm.model.remote.response.profile


import com.google.gson.annotations.SerializedName

data class EditProfileResponse(
    @SerializedName("success")
    val success: Boolean // true
)