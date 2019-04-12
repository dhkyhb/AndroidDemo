package com.example.androiddemo.ui

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import com.example.androiddemo.R
import kotlinx.android.synthetic.main.activity_reflect.*

class reflectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reflect)
        btn_shutdown.setOnClickListener {
            getServiceShutDown(true)
        }
        btn_reboot.setOnClickListener {
            getServiceShutDown(false)
        }
    }

    fun getServiceShutDown(isShutDown: Boolean) {
        try {

            val serviceClass = Class.forName("android.os.ServiceManager")
            //获取servicemanager里面的getservice方法
            val getServiceMethod = serviceClass.getDeclaredMethod("getService", String::class.java)
            getServiceMethod.isAccessible = true
            //调用getservice获取remoteService
            val oRemoteService = getServiceMethod.invoke(null, Context.POWER_SERVICE)
            // 获得IPowerManager.Stub类
            val cStub = Class.forName("android.os.IPowerManager\$Stub")
            //获取asInterface方法
            val methodAsInterface = cStub.getMethod("asInterface", IBinder::class.java)
            //调用asINterface方法获取IPowerManager对象
//        methodAsInterface.isAccessible = true
            val oIPowerManager = methodAsInterface.invoke(cStub, oRemoteService)
            if (isShutDown) {
                //获取shutdown方法
                val shutdownMethod =
                    oIPowerManager.javaClass.getMethod(
                        "shutdown",
                        Boolean::class.java,
                        String::class.java,
                        Boolean::class.java
                    )
                //调用shutdown方法
                shutdownMethod.invoke(oIPowerManager, true, null, false)
            } else {
                val rebootMethod =
                    oIPowerManager.javaClass.getMethod(
                        "reboot",
                        Boolean::class.java,
                        String::class.java,
                        Boolean::class.java
                    )
                rebootMethod.invoke(oIPowerManager, true, null, false)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}
