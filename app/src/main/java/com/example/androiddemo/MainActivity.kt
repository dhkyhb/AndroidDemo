package com.example.androiddemo

import android.annotation.TargetApi
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import com.example.androiddemo.androidframework.lifecycle.LifecycleActivity
import com.example.androiddemo.androidframework.navigation.navigationActivity
import com.example.androiddemo.androidframework.paging.PagingActivity
import com.example.androiddemo.base.BaseActivity
import com.example.androiddemo.eventbus.eventbusActivity
import com.example.androiddemo.extension.logDebug
import com.example.androiddemo.mvvm.ViewModelActivity
import com.example.androiddemo.ui.*
import com.example.androiddemo.ui.horizontalScroll.HorizontalScrollActivity
import com.example.androiddemo.utils.DeviceuuidFactory
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.sdk25.coroutines.__TextWatcher
import org.jetbrains.anko.sdk25.coroutines.textChangedListener
import org.jetbrains.anko.startActivity

class MainActivity : BaseActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun initListener() {
        btn_turnToNotification.setOnClickListener {
            startActivity<notificationActivity>()
        }
        btn_turnToretrofit.setOnClickListener {
            startActivity<retrofitActivity>()
        }
        btn_turnToReflect.setOnClickListener {
            startActivity<Main2Activity>()
        }
        btn_turnToTinker.setOnClickListener {
            startActivity<TinkerActivity>()
        }
        btn_turnToPicasso.setOnClickListener {
            startActivity<picassoActivity>()
        }
        btn_turnToWorrkActivity.setOnClickListener {
            startActivity<WorkNetActivity>()
        }
        btn_turnTohorizontalActivity.setOnClickListener {
            startActivity<HorizontalScrollActivity>()
        }
        btn_turnToEventBusActivity.setOnClickListener {
            startActivity<SmaliLogActivity>()
        }
        btn_turnLifecycleObserver.setOnClickListener {
            startActivity<LifecycleActivity>()
        }
        btn_turnViewModel.setOnClickListener {
            startActivity<ViewModelActivity>()
        }
        btn_turnPaging.setOnClickListener {
            startActivity<PagingActivity>()
        }
        btn_navigation.setOnClickListener {
            startActivity<navigationActivity>()
        }
        btn_shortcut.setOnClickListener {
            startActivity<BadgeActivity>()
        }
    }



}

private fun __TextWatcher.afterTextChanged() {
//    Log.e(MainActivity::class.java.simpleName, )
}
