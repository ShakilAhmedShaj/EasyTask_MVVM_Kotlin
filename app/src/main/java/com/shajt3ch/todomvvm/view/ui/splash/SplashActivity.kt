package com.shajt3ch.todomvvm.view.ui.splash

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.shajt3ch.todomvvm.R
import com.shajt3ch.todomvvm.model.local.AppPreferences
import com.shajt3ch.todomvvm.util.GeneralHelper
import com.shajt3ch.todomvvm.util.NetworkHelper
import com.shajt3ch.todomvvm.view.ui.auth.LoginActivity
import com.shajt3ch.todomvvm.view.ui.main.MainActivity
import com.shajt3ch.todomvvm.viewmodel.splash.SplashViewModel
import kotlinx.coroutines.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.intentFor

class SplashActivity : AppCompatActivity() {

    companion object {
        const val TAG = "SplashActivity"
    }

    private lateinit var viewModel: SplashViewModel
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var appPreferences: AppPreferences
    private val mContext = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //hiding status bar
        GeneralHelper.hideStatusBar(this)

        viewModel = ViewModelProvider(this).get(SplashViewModel::class.java)
        sharedPreferences = getSharedPreferences("com.shajt3ch.todomvvm.pref", Context.MODE_PRIVATE)
        appPreferences = AppPreferences(sharedPreferences)


        //splash delay
        CoroutineScope(Dispatchers.IO).launch {
            checkNetwork()
        }
    }

    //checking for internet connection
    suspend fun checkNetwork() {

        delay(2000L)

        val status = NetworkHelper.isNetworkConnected(this)

        if (status) {

            val token = "Bearer ${appPreferences.getAccessToken()}"
            //val token = appPreferences.getAccessToken()

            withContext(Dispatchers.Main)
            {
                token?.let {
                    viewModel.validateToken(it)
                        .observe(mContext, Observer {

                            if (it.code() == 200) {

                                val msg = it.body()

                                if (msg?.message == "true") {
                                    finish()
                                    startActivity(intentFor<MainActivity>())

                                } else {
                                    startActivity(intentFor<LoginActivity>())
                                }

                            }
                        })
                }
            }


        } else {

            withContext(Dispatchers.Main) {

                showNetworkDialogue()
            }
        }
    }


    private fun showNetworkDialogue() {
        alert {
            isCancelable = false
            title = getString(R.string.error_no_internet)
            message = getString(R.string.error_no_internet_msg)
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
