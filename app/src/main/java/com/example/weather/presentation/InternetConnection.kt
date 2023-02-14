package com.example.weather.presentation

import android.app.Application
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.util.Log
import androidx.lifecycle.LiveData

class InternetConnection(
    private val connectivityManager: ConnectivityManager,
) : LiveData<Boolean>() {

    constructor(appContext: Application) : this(
        appContext.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
    )

    private val networkCallback =
    object : ConnectivityManager.NetworkCallback() {

        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            Log.d("TAG", "onAvailable: Network $network is Available")
            postValue(true)
        }

        override fun onCapabilitiesChanged(
            network: Network,
            networkCapabilities: NetworkCapabilities,
        ) {
            val isInternet =
                networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            Log.d("TAG", "networkCapabilities: $network $networkCapabilities")
            val isValidated =
                networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
            if (isValidated) {
                Log.d("TAG", "hasCapability: $network $networkCapabilities")
            } else {
                Log.d("TAG",
                    "Network has No Connection Capability: $network $networkCapabilities")
            }
            postValue(isInternet && isValidated)
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            postValue(false)
        }
    }

    override fun onActive() {
        super.onActive()
        val builder = NetworkRequest.Builder()
        connectivityManager.registerNetworkCallback(builder
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build(), networkCallback)
    }

    override fun onInactive() {
        super.onInactive()
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}