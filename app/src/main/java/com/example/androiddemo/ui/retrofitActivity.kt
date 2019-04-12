package com.example.androiddemo.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.androiddemo.R
import com.example.androiddemo.base.JUHEAPI
import com.example.androiddemo.retrofit.RetrofitFactory
import com.example.androiddemo.base.baseObserver
import com.example.androiddemo.bean.juheBean.NbaResult
import com.example.androiddemo.bean.juheBean.RequestResult
import com.example.androiddemo.extension.logDebug
import com.example.androiddemo.retrofit.RetrofitConfig
import com.example.androiddemo.retrofit.RetrofitManager
import com.example.androiddemo.retrofit.interceptor.HeadersInterceptor
import com.example.androiddemo.services.juheServices
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

class retrofitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)

        acquireAgentTra()
    }

    private fun acquireAgentTra() {
        val config = RetrofitConfig
            .Builder()
            .addInterceptors(arrayListOf(HttpLoggingInterceptor(), HeadersInterceptor()) as List<Interceptor>)
            .build()
        RetrofitManager
            .getInstace()
            .setConfig(config)
            .createaService(juheServices::class.java)
            .acquireNBAGameI()

            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : baseObserver<RequestResult<NbaResult>>(){
                override fun onNext(t: RequestResult<NbaResult>) {
                    super.onNext(t)
                    if (t != null) {
                        if (t.error_code == 0) {
                            Log.e(retrofitActivity::class.java.simpleName, "histtoryResult: $t")
                        }else{
                            logDebug(retrofitActivity::class.java.simpleName, "获取数据不成功")
                            logDebug(retrofitActivity::class.java.simpleName, t.toString())
                        }
                    }
                }
            })
    }

}
