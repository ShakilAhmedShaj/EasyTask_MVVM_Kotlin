package com.decimalab.easytask.model.remote.response.profile


import com.google.gson.annotations.SerializedName

data class UserProfileResponse(
    @SerializedName("id")
    val id: Int, // 6
    @SerializedName("name")
    val name: String, // shaj
    @SerializedName("email")
    val email: String, // shaj@gmail.com
    @SerializedName("profile_image")
    val profileImage: String, // http://localhost/storage/profile_image/default_profile.png
    @SerializedName("profile_image_path")
    val profileImagePath: String, // /storage/profile_image/default_profile.png
    @SerializedName("bio")
    val bio: String // Stay Safe from Corona !.
)