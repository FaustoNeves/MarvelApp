package br.com.fausto.marvelapplication.ui.utils

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import androidx.lifecycle.MutableLiveData

class NetworkStateHelper(context: Context) : ConnectivityManager.NetworkCallback() {

    private val cm = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager;
    var connectionState = MutableLiveData<Boolean>()

    init {
        cm.registerDefaultNetworkCallback(ConnectivityCallback())
    }

    inner class ConnectivityCallback : ConnectivityManager.NetworkCallback() {
        override fun onCapabilitiesChanged(network: Network, capabilities: NetworkCapabilities) {
            val connected = capabilities.hasCapability(NET_CAPABILITY_INTERNET)
            connectionState.postValue(connected)
        }

        override fun onLost(network: Network) {
            connectionState.postValue(false)
        }
    }
}