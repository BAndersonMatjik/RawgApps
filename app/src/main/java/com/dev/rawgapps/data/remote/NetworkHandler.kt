package com.dev.rawgapps.data.remote

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

class NetworkHandler(context: Context) {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val networkStatus = callbackFlow<NetworkStatus> {
        val networkStatusCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onUnavailable() {
                trySend(NetworkStatus.Unavailable)
            }

            override fun onAvailable(network: Network) {
                trySend(NetworkStatus.Available)
            }

            override fun onLost(network: Network) {
                trySend(NetworkStatus.Unavailable)
            }
        }

        val request = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()
        connectivityManager.registerNetworkCallback(request, networkStatusCallback)

        awaitClose {
            connectivityManager.unregisterNetworkCallback(networkStatusCallback)
        }
    }

}

sealed interface NetworkStatus{
    object Available : NetworkStatus
    object Unavailable : NetworkStatus
}