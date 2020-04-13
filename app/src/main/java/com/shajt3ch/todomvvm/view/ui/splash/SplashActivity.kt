package com.shajt3ch.todomvvm.view.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import com.shajt3ch.todomvvm.R
import com.shajt3ch.todomvvm.util.GeneralHelper
import com.shajt3ch.todomvvm.util.NetworkHelper
import com.shajt3ch.todomvvm.view.ui.auth.LoginActivity
import kotlinx.coroutines.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.intentFor

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //hide status bar
        GeneralHelper.hideStatusBar(this)

        CoroutineScope(Dispatchers.IO).launch {
            //delay for splash
            delay(2000L)
            checkNetwork()
        }
    }

    private suspend fun checkNetwork() {

//        delay(2000L)

        val status = NetworkHelper.isNetworkConnected(this)

        if (status) {
            finish()
            startActivity(intentFor<LoginActivity>())

        } else {
            //running in main thread
            withContext(Dispatchers.Main) {
                showAlertDialog()
            }
        }
    }

    fun showAlertDialog() {
        alert {
            isCancelable = false
            title = getString(R.string.error_no_internet)
            message = getString((R.string.error_no_internet_msg))
            positiveButton("OK") {
                it.dismiss()

                val intent = Intent(Settings.ACTION_NETWORK_OPERATOR_SETTINGS)
                startActivity(intent)
            }
        }.show()
    }

    override fun onRestart() {
        super.onRestart()
        CoroutineScope(Dispatchers.IO).launch {
            checkNetwork()
        }
    }
}


