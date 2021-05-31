package com.example.sixtcars.data.remote.interceptor

import android.content.Context
import com.example.sixtcars.utils.NetworkUtil
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import kotlin.jvm.Throws

class NetworkInterceptor(private val context: Context) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        if (NetworkUtil.isConnected(context)) {
            request.newBuilder()
                .header("Cache-Control", "public, max-age=" + 60)
                .build()

        } else {
            request.newBuilder()
                .header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 30)
                .build()
        }

        return chain.proceed(request)
    }
}
