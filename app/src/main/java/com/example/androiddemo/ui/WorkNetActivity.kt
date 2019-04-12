package com.example.androiddemo.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.androiddemo.R
import com.example.androiddemo.base.*
import com.example.androiddemo.extension.acquireRetrofitIns
import com.example.androiddemo.extension.doInBackground
import com.example.androiddemo.extension.logDebug
import com.example.androiddemo.retrofit.interceptor.HeadersInterceptor
import com.example.androiddemo.services.*
import com.example.androiddemo.utils.MD5Utils
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_work_net.*
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import java.io.IOException
import java.util.concurrent.TimeUnit

class WorkNetActivity : AppCompatActivity() {

    private val TIMEOUT = 10
    lateinit var content: String
    lateinit var sign: String
    var jsonObject: JSONObject? = null
    val JSON = MediaType
        .parse("application/json; charset=utf-8")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_net)

        btn_sendSMS.setOnClickListener {
            sendSMS(et_tel.text.toString())
//            sendSMS2(et_tel.text.toString())
        }
    }

    private fun sendSMS(tel: String) {
        val json = paramsMethod(tel)
        val toJson = json.toString()
        val requestBody = RequestBody.create(
            JSON, toJson
        )
        acquireRetrofitIns(WorkServices::class.java)
            .sendSMS(requestBody)
            .doInBackground(object : baseObserver<Result>() {
                override fun onNext(t: Result) {
                    super.onNext(t)
                    val result = Gson().fromJson(t.result, resultBean::class.java)
                    if (result.code.equals("000000")) {
                        tv_hint.text = result.toString()
                    } else {
                        tv_hint.text = result.message
                    }
                }
                override fun onError(e: Throwable) {
                    super.onError(e)
                    logDebug("onError")
                    Log.e(WorkNetActivity::class.java.simpleName, "onErrore")
                    e.printStackTrace()
                }
            })
    }

    private fun sendSMS2(tel: String) {
        val json = paramsMethod(tel)
        logDebug("json: $json")
        val body = RequestBody.create(JSON, json.toString())
        val request = Request.Builder().url(BASEAPI + LOGIN_SMS).post(body).build()
        val client = OkHttpClient.Builder()
            .connectTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor())
            .addInterceptor(HeadersInterceptor())
            .build()
        val call = client.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e(WorkNetActivity::class.java.simpleName, "onFailure: ${e.printStackTrace()}")
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val bodyResult = response.body()?.string()
                    Log.e(WorkNetActivity::class.java.simpleName, "connect success")
                    Log.e(WorkNetActivity::class.java.simpleName, "Reponse body: $bodyResult")
                    val result = JSONObject(bodyResult)
//                    Log.e(WorkNetActivity::class.java.simpleName, "${jsonObject1.getString("result")}")
                    val fromJson: resultBean =
                        Gson().fromJson(result.getString("result"), resultBean::class.javaObjectType)
                    Log.e(WorkNetActivity::class.java.simpleName, "$fromJson")
                } else {
                    Log.e(WorkNetActivity::class.java.simpleName, "error: ${response.message()}")
                }
            }
        })
    }

    private fun acquireData(tel: String): JSONObject {
        if (jsonObject != null)
            return jsonObject as JSONObject
        jsonObject = JSONObject()
        jsonObject!!.put("apiVersion", API_VERSION)
        jsonObject!!.put("txnDate", System.currentTimeMillis().toString())
        jsonObject!!.put("encryptId", ENCRYPT_ID)
        jsonObject!!.put("mobileMac", MOBILEMAC)
        jsonObject!!.put("method", LOGIN_SMS)
//        jsonObject!!.put("mid", "")
        jsonObject!!.put("agencyId", AGENT_ID)
        jsonObject!!.put("cellPhone", tel)
        return jsonObject as JSONObject
    }

    fun paramsMethod(tel: String): JSONObject {
        val content = JSONObject()
//        val requestBean = (acquireData(tel)).toString()
        val requestBean = Content(tel)
        content.put("content", Gson().toJson(requestBean))
        content.put("key", KEY)
        var contentStr = Gson().toJson(requestBean)

        contentStr = contentStr.replace("\\\"".toRegex(), "\\\\\\\"")
        val signStr = "{\"content\":\"$contentStr\",\"key\":\"$KEY\"}"
        val sign = MD5Utils.shaEncrypt(signStr)
        content.remove("key")
        content.put("sign", sign)
        Log.e(WorkNetActivity::class.java.simpleName, "content: ${content.toString()}")
//        return content.toString()
        return content
    }

}
