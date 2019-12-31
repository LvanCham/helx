package com.cham.alice.base.network

import com.blankj.utilcode.util.LogUtils
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Hello World
 * Date: 2019/12/18
 * Author: Cham
 */
class LoggingInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val t1 = System.nanoTime()//请求发起的时间

        LogUtils.e(
            String.format(
                "请求URL------%s on %s%n请求头------%s",
                request.url, chain.connection(), request.headers
            )
        )
        val response = chain.proceed(request)

        val t2 = System.nanoTime()//收到响应的时间

        val responseBody = response.peekBody((1024 * 1024).toLong())

        LogUtils.e(
            String.format(
                "响应URL-------: %s %n响应数据------%s 请求用时--------%.1fms%n%s",
                response.request.url,
                responseBody.string(),
                (t2 - t1) / 1e6,
                response.headers
            )
        )
        return response
    }
}