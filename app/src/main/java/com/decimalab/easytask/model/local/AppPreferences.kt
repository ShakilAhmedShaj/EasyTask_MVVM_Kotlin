package com.decimalab.easytask.model.local

import android.content.SharedPreferences

/**
 * Created by Shakil Ahmed Shaj on 15,April,2020.
 * shakilahmedshaj@gmail.com
 */
class AppPreferences(private val prefs: SharedPreferences) {

    companion object {
        const val KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN"
        const val KEY_TOKEN_ID = "PREF_KEY_TOKEN_ID"
        const val KEY_USER_ID = "PREF_KEY_USER_ID"
        const val KEY_USER_NAME = "PREF_KEY_USER_NAME"
        const val KEY_USER_EMAIL = "PREF_KEY_USER_EMAIL"
        const val KEY_THEME_MODE = "PREF_KEY_THEME_MODE"
    }

    fun setAccessToken(access_token: String) {
        prefs.edit().putString(KEY_ACCESS_TOKEN, access_token).apply()
    }

    fun setTokenId(token_id: String) {
        prefs.edit().putString(KEY_TOKEN_ID, token_id).apply()
    }

    fun setUserId(user_id: Int) {
        prefs.edit().putInt(KEY_USER_ID, user_id).apply()
    }

    fun setUserName(user_name: String) {
        prefs.edit().putString(KEY_USER_NAME, user_name).apply()
    }

    fun setUserEmail(user_email: String) {
        prefs.edit().putString(KEY_USER_EMAIL, user_email).apply()
    }

    fun setThemeMode(value: Int) {
        prefs.edit().putInt(KEY_THEME_MODE, value).apply()
    }

    fun getAccessToken(): String? = prefs.getString(KEY_ACCESS_TOKEN, null)

    fun getTokenId(): String? = prefs.getString(KEY_TOKEN_ID, null)

    fun getUserId(): Int? = prefs.getInt(KEY_USER_ID, 0)

    fun getUserName(): String? = prefs.getString(KEY_USER_NAME, null)

    fun getUserEmail(): String? = prefs.getString(KEY_USER_EMAIL, null)

    fun getThemeMode(): Int = prefs.getInt(KEY_THEME_MODE, 0)

}