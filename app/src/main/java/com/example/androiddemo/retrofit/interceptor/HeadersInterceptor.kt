package com.example.androiddemo.retrofit.interceptor

import com.example.androiddemo.extension.logDebug
import okhttp3.Interceptor
import okhttp3.Response

//retrofit 的头部信息统一添加拦截器
class HeadersInterceptor : Interceptor{

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.request(). let{
            logDebug("request: ${it.url()}")
            it.newBuilder().addHeader("Content-Type", "application/json")
                .addHeader("charset", "UTF-8")

        chain.proceed(it)
        }
    }

}