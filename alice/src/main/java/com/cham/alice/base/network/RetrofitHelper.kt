package com.cham.alice.base.network
import android.os.Environment
import com.cham.alice.appConstant.HOST
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * Hello World
 * Date: 2019/12/18
 * Author: Cham
 */
class RetrofitHelper  private constructor() {

    private val APP_DIR = Environment.getExternalStorageDirectory().absolutePath + "/LongShine/"




    companion object {
        @JvmStatic
        fun getInstance(): RetrofitHelper {//全局访问点
            return SingletonHolder.mInstance
        }
        private lateinit var retrofit: Retrofit

    }
    private object SingletonHolder {//静态内部类
    val mInstance: RetrofitHelper = RetrofitHelper()
    }
    private fun readResolve(): Any {//防止单例对象在反序列化时重新生成对象
        return SingletonHolder.mInstance
    }

    init {
        retrofit =getRetrofitBuilder().build()

    }

    fun getOkHttpClient(): OkHttpClient {
        // 定制OkHttp
        val httpClientBuilder = OkHttpClient.Builder()
        // OkHttp进行添加拦截器loggingInterceptor
        httpClientBuilder.readTimeout(20000, TimeUnit.MILLISECONDS)
        httpClientBuilder.writeTimeout(20000, TimeUnit.MILLISECONDS)
        httpClientBuilder.connectTimeout(20000, TimeUnit.MILLISECONDS)
        httpClientBuilder.addInterceptor(HttpHeaderInterceptor())
        httpClientBuilder.addInterceptor(HttpCacheInterceptor())
        httpClientBuilder.addInterceptor(LoggingInterceptor())
        httpClientBuilder.addNetworkInterceptor(HttpCacheInterceptor())
        httpClientBuilder.cache(Cache(File(APP_DIR, "ok_cache"), 1024 * 1024 * 30L))

        return httpClientBuilder.build()
    }

    private fun getRetrofitBuilder(): Retrofit.Builder {
        val gson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create()
        val okHttpClient = getOkHttpClient()
        return Retrofit.Builder().client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(HOST)

    }
    private fun getRetrofitBuilder(url: String): Retrofit.Builder {
        val mGson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create()
        val okHttpClient = getOkHttpClient()
        return Retrofit.Builder().client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(mGson))
            .baseUrl(url)
    }


    fun <T> getApiService(cls: Class<T>, baseUrl: String): T {
        val retrofit =getRetrofitBuilder(baseUrl).build()
        return retrofit.create(cls)
    }



    fun <T> create(service: Class<T>?): T {
        return  retrofit.create(service!!) ?: throw RuntimeException("Api service is null!")
    }

}