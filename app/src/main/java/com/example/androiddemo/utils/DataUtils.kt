package com.example.androiddemo.utils

import java.lang.StringBuilder
import java.text.SimpleDateFormat
import java.util.*

class DataUtils {

    companion object {
        fun acquireDate(): String {
            val date = Date()
            val sdf = SimpleDateFormat("MM/dd")
            return sdf.format(date).let {
                var sb = it
                if (it.substring(3, 4).equals("0")) {
                    sb = it.replaceRange(3, 4, "")
                }
                if (sb.substring(0, 1).equals("0")) {
                    sb.replaceRange(0, 1, "")
                } else {
                    sb
                }

            }
        }
    }

}