package com.shajt3ch.easytask.util.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import com.google.gson.JsonParser
import retrofit2.Response

object NetworkHelper {

    fun isNetworkConnected(context: Context): Boolean{
        var result = false
        (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                result =
                    isCapableNetwork(
                        this,
                        this.activeNetwork
                    )
            } else {
                val networkInfos = this.allNetworks
                for (tempNetworkInfo in networkInfos) {
                    if(isCapableNetwork(
                            this,
                            tempNetworkInfo
                        )
                    )
                        result =  true
                }
            }
        }

        return result
    }

    private fun isCapableNetwork(cm: ConnectivityManager,network: Network?): Boolean{
        cm.getNetworkCapabilities(network)?.also {
            if (it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                return true
            } else if (it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                return true
            }
        }
        return false
    }

    fun handelNetworkError(response: Response<*>): NetworkError {

        val error = response.errorBody()?.string()
        val message = JsonParser().parse(error)
            .asJsonObject["error"]
            .toString()

        return NetworkError(response.code(), message)
    }
}