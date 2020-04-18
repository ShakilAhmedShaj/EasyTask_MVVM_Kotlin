package com.shajt3ch.todomvvm.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build

object NetworkHelper {

    fun isNetworkConnected(context: Context): Boolean{
        var result = false
        (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                result = isCapableNetwork(this,this.activeNetwork)
            } else {
                val networkInfos = this.allNetworks
                for (tempNetworkInfo in networkInfos) {
                    if(isCapableNetwork(this,tempNetworkInfo))
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
}