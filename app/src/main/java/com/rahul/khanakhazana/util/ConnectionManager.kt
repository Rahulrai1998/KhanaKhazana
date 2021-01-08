package com.rahul.khanakhazana.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class ConnectionManager {

    //following function will get the information that there is network or not
    fun checkConnectivity(context:Context):Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager/* this is the
        Connectivity Manager defined here , it will give
        information about the currently active network */

        val activeNetwork:NetworkInfo? = connectivityManager.activeNetworkInfo //activeNetworkInfo method fetch data of active networks

        //isConnected method checks that the network has internet or not....
        if(activeNetwork?.isConnected!=null){

            return activeNetwork.isConnected
        }
        else
        {
            return false
        }



    }


}