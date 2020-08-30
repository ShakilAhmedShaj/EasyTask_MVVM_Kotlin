package com.decimalab.easytask

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import com.decimalab.easytask.model.local.AppPreferences
import com.decimalab.easytask.util.AppThemeMode
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by Shakil Ahmed Shaj on 14,May,2020.
 * shakilahmedshaj@gmail.com
 */
class BaseApplication : Application() {
    private lateinit var appPreferences: AppPreferences
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate() {
        super.onCreate()
        //pref
        sharedPreferences = this.getSharedPreferences(BuildConfig.PREF_NAME, Context.MODE_PRIVATE)
        appPreferences = AppPreferences(sharedPreferences)
        //get the theme mode from pref
        getThemMode()

    }

    private fun getThemMode() {

        CoroutineScope(Dispatchers.IO).launch {
            var mode = 0

            if (appPreferences.getThemeMode() == 0) {
                if (isPreAndroidTen()) {
                    mode = AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
                } else {
                    mode = AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                }
            } else {
                mode = when (appPreferences.getThemeMode()) {
                    AppThemeMode.LIGHT.ordinal -> AppCompatDelegate.MODE_NIGHT_NO
                    AppThemeMode.DARK.ordinal -> AppCompatDelegate.MODE_NIGHT_YES
                    AppThemeMode.SYSTEM.ordinal -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                    AppThemeMode.BATTERY.ordinal -> AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
                    else -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                }
            }
            AppCompatDelegate.setDefaultNightMode(mode)
        }
    }

    private fun isPreAndroidTen() = Build.VERSION.SDK_INT < Build.VERSION_CODES.Q


}