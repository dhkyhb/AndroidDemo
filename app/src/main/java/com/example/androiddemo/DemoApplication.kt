package com.example.androiddemo

import android.app.Application
import android.support.multidex.MultiDex
import com.example.androiddemo.common.BUGLY_APPID
import com.tencent.bugly.Bugly
import com.tencent.tinker.loader.app.TinkerApplication
import com.tencent.tinker.loader.shareutil.ShareConstants


/**
 * 这个类不做任何代码处理，所有类放到applicationlike
 */
open class DemoApplication : TinkerApplication(
    com.tencent.tinker.loader.shareutil.ShareConstants.TINKER_ENABLE_ALL, "com.example.androiddemo.DemoApplicatioinLike",
    "com.tencent.tinker.loader.TinkerLoader", false)
