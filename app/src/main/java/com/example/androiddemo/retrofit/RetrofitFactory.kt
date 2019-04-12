package com.example.androiddemo.retrofit

import com.example.androiddemo.common.API
import com.example.androiddemo.retrofit.interceptor.HeadersInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import java.util.concurrent.TimeUnit

class RetrofitFactory private constructor() {

    private lateinit var retrofitBuilder: Retrofit.Builder
    private lateinit var retrofit: Retrofit

    companion object {
        val retrofitFactory: RetrofitFactory by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            RetrofitFactory()
        }
    }

    init {
        retrofitBuilder = Retrofit.Builder()
            .baseUrl(API)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okhttpClient())
    }

    private fun okhttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor())
            .addInterceptor(HeadersInterceptor())
            .build()
    }

    fun <T> createService(service: Class<T>): T {
        try {
            return retrofit.create(service)//防止retrofit latinit未进行初始化
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            retrofit = retrofitBuilder.build()
            return retrofit.create(service)
        }
    }

    fun baseUrl(api: String): RetrofitFactory {
        retrofitBuilder.baseUrl(api)
        retrofit = retrofitBuilder.build()
        return this
    }

    fun build(): RetrofitFactory {
        return this
    }

}

