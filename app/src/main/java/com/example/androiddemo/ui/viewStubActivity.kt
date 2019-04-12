package com.example.androiddemo.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.androiddemo.R
import kotlinx.android.synthetic.main.activity_view_stub.*

class viewStubActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_stub)

        btn_show_stub.setOnClickListener {
            val inflate = vs_Layout_icon.inflate()
            val iv_icon = inflate.findViewById<ImageView>(R.id.iv_icon)
            iv_icon.setOnClickListener {
                iv_icon.setImageResource(R.drawable.tulaoshi)
            }
        }

    }
}
