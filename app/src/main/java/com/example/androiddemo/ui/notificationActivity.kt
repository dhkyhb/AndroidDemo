package com.example.androiddemo.ui

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
import android.support.v4.app.NotificationCompat
import android.view.View
import com.example.androiddemo.R
import com.example.androiddemo.ui.shortcut.shortCutUtils
import kotlinx.android.synthetic.main.activity_notification.*

class notificationActivity : AppCompatActivity(), View.OnClickListener{

    var channel : NotificationChannel? = null
        get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel("channelId", "channelName", NotificationManager.IMPORTANCE_NONE)
        } else {
            null
        }
    var manager: NotificationManager? = null
    lateinit var notification: Notification

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
        initNotifiation()

        btn_checkPermission.setOnClickListener {
            sengChatMsg()
        }
    }
    override fun onClick(v: View?) {

    }

    private fun sengChatMsg() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            var channel: NotificationChannel = manager!!.getNotificationChannel("channelId")
            if (channel.importance == NotificationManager.IMPORTANCE_NONE) {
                var intent = Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS)
                intent.putExtra(Settings.EXTRA_APP_PACKAGE, packageName)
                intent.putExtra(Settings.EXTRA_CHANNEL_ID, "channelId")
                startActivity(intent)
                System.out.print("前往通知通告栏")
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    private fun initNotifiation() {
        if (manager == null) {
            manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager!!.createNotificationChannel(channel!!)
        }

        notification = Notification.Builder(this, "channelId")
            .setContentText("这是一个测试Text")
            .setContentTitle("这是一个测试Title")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher))
            .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
//            .setNumber(6)
            .setAutoCancel(true)
            .build()
        manager!!.notify(1, notification)
        shortCutUtils.changedMiUiShortCut(notification, 8)
    }

}
