package com.example.androiddemo.ui

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import com.bumptech.glide.Glide
import com.example.androiddemo.R
import com.example.androiddemo.base.BaseActivity

import kotlinx.android.synthetic.main.activity_glide.*
import kotlinx.android.synthetic.main.content_glide.*

class glideActivity : BaseActivity() {

    var imgUrl_longjuan =
        "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1553664388593&di=755a62461912f90863b27b37cab19526&imgtype=0&src=http%3A%2F%2Fres.hpoi.net.cn%2Fgk%2Fpic%2Fs%2F2017%2F02%2Fbc3dbbc5c70f40db9b2579af70178200.jpeg"
    var imgUrl_error = "https://timgsa.baidu.com/dddddddyyyyyyhhhhhbbbb.jpg"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glide)
        setSupportActionBar(toolbar)
    }

    override fun initListener() {
        super.initListener()
        btn_glide_loag.setOnClickListener {
            Glide.with(this).load(imgUrl_longjuan).into(iv_showImg)
        }
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

}
