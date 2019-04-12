package com.example.androiddemo.services

import com.example.androiddemo.base.*
import com.example.androiddemo.bean.juheBean.*
import com.example.androiddemo.utils.DataUtils
import io.reactivex.Observable
import io.reactivex.Observer
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.http.*

data class requestBody(var content: Content, var sign: String)

data class ContentBody(var content: Content, var key: String = KEY)

data class Content(var cellPhone: String, var apiVersion: String = API_VERSION, var encryptId: String = ENCRYPT_ID
                   , var txnDate: String = System.currentTimeMillis().toString(), var mobileMac: String = MOBILEMAC
                   , var method: String = LOGIN_SMS, var agencyId: String = AGENT_ID)

data class Result(var result: String, var sign : String?)

data class resultBean(var code: String, var data : DataBean?, var message: String)

data class DataBean(var code : String, var uuid: String)

interface WorkServices {

//    @Headers("Content-Type: application/json;charset=UTF-8")
    @POST(LOGIN_SMS)
    fun sendSMS(@Body body : RequestBody) : Observable<Result>

}