package com.cham.alice.base.network

import com.blankj.utilcode.util.NetworkUtils
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Hello World
 * Date: 2019/12/18
 * Author: Cham
 */
class HttpCacheInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        if (!NetworkUtils.isConnected()) {
            request = request.newBuilder()
                .cacheControl(CacheControl.FORCE_CACHE)
                .build()
        }
        val originalResponse = chain.proceed(request)
        return if (NetworkUtils.isConnected()) {
            val cacheControl = request.cacheControl.toString()
            originalResponse.newBuilder()
                .removeHeader("Pragma")
                .header("Cache-Control", cacheControl)
                .build()
        } else {
            originalResponse.newBuilder()
                .removeHeader("Pragma")
                .header("Cache-Control", "public, only-if-cached, max-stale=2419200")
                .build()
        }
    }
}