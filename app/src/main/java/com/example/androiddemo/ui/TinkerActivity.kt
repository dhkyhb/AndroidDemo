package com.example.androiddemo.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.androiddemo.R
import com.example.androiddemo.base.BaseActivity
import com.example.androiddemo.extension.logInfo
import kotlinx.android.synthetic.main.activity_tinker.*
import org.jetbrains.anko.toast

class TinkerActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tinker)
    }

    override fun initListener() {
        btn_start_tinker.setOnClickListener {
            var str:String? = null
//            logInfo("This is a bug class, str size is " + str!!.length)
            toast("this is fix class")
        }
    }
}
