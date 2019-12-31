package com.cham.alice.base.network

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Hello World
 * Date: 2019/12/18
 * Author: Cham
 * 配置请求头
 */
class HttpHeaderInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val accessToken = "token"
        val tokenType = "tokenType"
        val request = chain.request().newBuilder()
            .header("app_key", "appId")
            .header("Authorization", "$tokenType $accessToken")
            .header("Content-Type", "application/json")
            .addHeader("Connection", "close")
            .addHeader("Accept-Encoding", "identity")
            .build()
        return chain.proceed(request)
    }
}