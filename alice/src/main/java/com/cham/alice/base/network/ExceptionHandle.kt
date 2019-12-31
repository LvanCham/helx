package com.cham.alice.base.network

import android.net.ParseException
import android.util.Log
import com.google.gson.JsonParseException
import com.google.gson.stream.MalformedJsonException

import org.apache.http.conn.ConnectTimeoutException
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException

object ExceptionHandle {

    fun handleException(e: Throwable): ResponseThrowable {
        val ex: ResponseThrowable
        if (e is HttpException) {
            ex = ResponseThrowable(ERROR.NETWORD_ERROR)
        } else if (e is JsonParseException
            || e is JSONException
            || e is ParseException || e is MalformedJsonException
        ) {
            ex = ResponseThrowable(ERROR.PARSE_ERROR)
        } else if (e is ConnectException) {
            ex = ResponseThrowable(ERROR.NETWORD_ERROR)
        } else if (e is javax.net.ssl.SSLException) {
            ex = ResponseThrowable(ERROR.SSL_ERROR)
        } else if (e is ConnectTimeoutException) {
            ex = ResponseThrowable(ERROR.TIMEOUT_ERROR)
        } else if (e is java.net.SocketTimeoutException) {
            ex = ResponseThrowable(ERROR.TIMEOUT_ERROR)
        } else if (e is java.net.UnknownHostException) {
            ex = ResponseThrowable(ERROR.TIMEOUT_ERROR)
        } else {
            ex = if (e.message.isNullOrEmpty()) ResponseThrowable(
                1000,
                e.message!!
            ) else {
                Log.e("未知错误？？",""+e.toString())
                ResponseThrowable(ERROR.UNKNOWN)
            }
        }
        return ex
    }
}