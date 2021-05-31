package com.example.sixtcars.ui.base

import androidx.lifecycle.ViewModel
import com.example.sixtcars.utils.AppController
import com.example.sixtcars.utils.NetworkUtil
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

open class BaseViewModel : ViewModel() {

    fun isEmptyList(list: MutableList<*>?): Boolean {
        return list.isNullOrEmpty()
    }

    fun errorHandler(it: Throwable): String {
        var message = ""

        if (NetworkUtil.isConnected(AppController.appContext)) {
            message = when (it) {
                is SocketTimeoutException -> "Timeout has occurred on a socket read or accept."

                is UnknownHostException -> "IP address of a host could not be determined."
                else -> {
                    when ((it as? HttpException)?.response()?.code()) {
                        500 -> "Internal Server Error"
                        502 -> "Bad Gateway Error"
                        503 -> "Service Unavailable"
                        else -> "Something went wrong!"
                    }
                }
            }
        } else {
            message = "Check your internet connection"
        }

        return message
    }
}