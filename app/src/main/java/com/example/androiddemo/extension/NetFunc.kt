package com.example.androiddemo.extension

import com.example.androiddemo.base.BASEAPI
import com.example.androiddemo.retrofit.RetrofitFactory


fun <T> acquireRetrofitIns(servicesCls: Class<T>, baseApi : String = BASEAPI): T {
    return RetrofitFactory.retrofitFactory.baseUrl(baseApi).build().createService(servicesCls)
}