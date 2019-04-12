package com.example.androiddemo.base

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.example.androiddemo.callback.PermissionListener
import com.example.androiddemo.extension.logWarn
import com.example.androiddemo.utils.ActivityCollector
import java.lang.ref.WeakReference
import java.security.acl.Permission

open class BaseActivity : AppCompatActivity(), View.OnClickListener{

    /**
     * 判断当前Activity是否在前台。
     */
    protected var isActive: Boolean = false
    /**
     *当前activity实例
     */
    protected var activity: Activity? = null

    private var weakRefActivity: WeakReference<Activity>? = null

    var toolbar: Toolbar? = null

    private var mListener: PermissionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity = this
        weakRefActivity = WeakReference(this)
        ActivityCollector.add(weakRefActivity)
    }

    override fun onResume() {
        super.onResume()
        isActive = true
    }

    override fun onPause() {
        super.onPause()
        isActive = false
    }

    override fun onDestroy() {
        super.onDestroy()
        activity = null
        ActivityCollector.remove(weakRefActivity)
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        initListener()
    }

    override fun onClick(v: View?) {
        widgetClick(v)
    }

    protected open fun widgetClick(v: View?) {

    }

    protected open fun initListener(){

    }

    /**
     * 检查和处理运行时权限，并将用户授权的结果通过PermissionListener进行回调。
     *
     * @param permissions
     * 要检查和处理的运行时权限数组
     * @param listener
     * 用于接收授权结果的监听器
     */
    protected fun handlePermission(permissions: Array<String>?, listener:PermissionListener) {
        if (permissions == null || activity == null) {
            return
        }
        mListener = listener
        val requestPermissionList = ArrayList<String>()
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(activity!!, permission) != PackageManager.PERMISSION_GRANTED) {
                requestPermissionList.add(permission)
            }
        }
        if (!requestPermissionList.isEmpty()) {
            ActivityCompat.requestPermissions(activity!!, requestPermissionList.toTypedArray(), 1)
        }else{
            listener.onGranted()
        }
    }

    /**
     * 隐藏软键盘
     */
    fun hideSoftKeyboard() {
        try {
            val view = currentFocus
            if (view != null) {
                val binder = view.windowToken
                val manager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            }
        } catch (e: Exception) {
            logWarn(TAG, e.message, e)
        }
    }

    /**
     * 显示软键盘。
     */
    fun showSoftKeyboard(editText: EditText?) {
        try {
            if (editText != null) {
                editText.requestFocus()
                val manager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                manager.showSoftInput(editText, 0)
            }
        } catch (e: Exception) {
            logWarn(TAG, e.message, e)
        }
    }

    companion object {

        private const val TAG = "BaseActivity"
    }

}