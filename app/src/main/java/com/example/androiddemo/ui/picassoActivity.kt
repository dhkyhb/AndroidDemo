package com.example.androiddemo.ui

import android.graphics.Bitmap
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.View
import android.widget.LinearLayout
import com.example.androiddemo.R
import com.example.androiddemo.base.BaseActivity
import com.example.androiddemo.extension.SnackBarLong
import com.example.androiddemo.extension.SnackBarShort
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import io.reactivex.*
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers

import kotlinx.android.synthetic.main.activity_picasso.*
import kotlinx.android.synthetic.main.content_picasso.*
import org.jetbrains.anko.linearLayout
import java.lang.Exception
import java.lang.StringBuilder
import java.util.*

class picassoActivity : BaseActivity() {

    var imgUrl_longjuan =
        "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1553664388593&di=755a62461912f90863b27b37cab19526&imgtype=0&src=http%3A%2F%2Fres.hpoi.net.cn%2Fgk%2Fpic%2Fs%2F2017%2F02%2Fbc3dbbc5c70f40db9b2579af70178200.jpeg"
    var imgUrl_error = "https://timgsa.baidu.com/dddddddyyyyyyhhhhhbbbb.jpg"
    var clickTimes = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picasso)
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        setSupportActionBar(toolbar)
    }


    override fun initListener() {
        //加载网络图片
        btn_picasso_odownload.setOnClickListener {
            iv_showImg.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            Picasso.get().setIndicatorsEnabled(true)//图片显示是从网络、缓存还是磁盘中获取.
            Picasso.get().isLoggingEnabled = true//打印debug时候的log
            Picasso.get()
                .load(imgUrl_longjuan)
                .into(iv_showImg)
            it.SnackBarLong("load success", "I konw", null)
        }
        //加载网络图片并旋转
        btn_picasso_odownload_rotate.setOnClickListener {
            Picasso.get().load(imgUrl_longjuan).rotate(180.0f).into(iv_showImg)
            it.SnackBarLong("load success", "I konw", null)
        }
        //加载网络图片并根据xy坐标点旋转
        btn_picasso_odownload_rotate_xy.setOnClickListener {
            Picasso.get().load(imgUrl_longjuan).rotate(180.0f, 20.0F, 20.0F).into(iv_showImg)
            it.SnackBarLong("load success", "I konw", null)
        }
        //加载失败显示error图片
        btn_picasso_odownload_error.setOnClickListener {
            Picasso.get().load(imgUrl_error).error(R.drawable.tulaoshi).into(iv_showImg)
            it.SnackBarLong("load error icon", "I konw", null)
        }
        //显示resize之后的图片
        btn_picasso_resize.setOnClickListener {
            //直接写是pixels格式，dp则是下面这种写法
            Picasso.get().load(R.drawable.longzhu_wukong_god).resize(R.dimen.error_width, R.dimen.error_height)
                .onlyScaleDown()//只有当原图片大于尺寸的时候才会缩放，resize才能起效果
                .centerCrop()//充满ImageView 的边界，居中裁剪。
                .into(iv_showImg)
            it.SnackBarLong("load error icon resize", "I konw", null)
        }
        //显示resize之后的图片 scaletype centerinside
        btn_picasso_resize_centerInside.setOnClickListener {
            //直接写是pixels格式，dp则是下面这种写法
            Picasso.get().load(R.drawable.longzhu_wukong_god).resize(R.dimen.error_width, R.dimen.error_height)
                .onlyScaleDown()//只有当原图片大于尺寸的时候才会缩放，resize才能起效果
                .centerInside()//centercrop可能看不全，这个充满边界，只有图片大于尺寸.才会起效果
                .into(iv_showImg)
            it.SnackBarLong("load error icon resize", "I konw", null)
        }
        //fit方法
        btn_picasso_resize_fit.setOnClickListener {
            iv_showImg.layoutParams = LinearLayout.LayoutParams(300, 400)
            //直接写是pixels格式，dp则是下面这种写法
            Picasso.get().load(R.drawable.longzhu_wukong_god)
                .fit()//fit 直接计算大小，然后执行resize。可能会出现拉伸扭曲情况，与centercrop同时使用 imageview宽高不能为wrap_content
                .centerCrop()
                .into(iv_showImg)
            it.SnackBarLong("load error icon resize", "I konw", null)
        }
        //placeholder  加载未完成显示图片
        btn_picasso_odownload_placeholer.setOnClickListener {
            Picasso.get().load(imgUrl_error).placeholder(R.drawable.diyudelanfeng).into(iv_showImg)
            it.SnackBarLong("load placeHolder icon", "I konw", null)
        }
        //获取图片 根据尺寸扩大2倍然后显示
        btn_acquire_bitmap_by_scale_2.setOnClickListener {
            Observable.create(ObservableOnSubscribe<Bitmap> { emitter ->
                val bitmap = Picasso.get().load(imgUrl_longjuan).placeholder(R.drawable.diyudelanfeng).get()
                emitter.onNext(bitmap)
            })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map {
                    _bitmap ->
                    Bitmap.createScaledBitmap(_bitmap, it.width * 2, it.height * 2, false)
                }
                .subscribe(object : Consumer<Bitmap>{
                    override fun accept(t: Bitmap?) {
                        iv_showImg.setImageBitmap(t!!)
                    }

                })
//            Picasso.get().load(imgUrl_longjuan).placeholder(R.drawable.diyudelanfeng).into(iv_showImg)
            it.SnackBarLong("load placeHolder icon", "I konw", null)
        }
        fab.setOnClickListener { view ->
            var sb = StringBuilder()
            if (clickTimes <= 3) {
                clickTimes++
                for (_times in 0..clickTimes) {
                    sb.append("~")
                }
            } else {
                clickTimes = 2
                sb.clear()
                sb.append("~")
            }
            Snackbar.make(view, "nothing happened$sb", Snackbar.LENGTH_LONG)
                .setAction("嘤嘤嘤", null).show()
        }
    }

    companion object {
        private var TAG = picassoActivity::class.java
    }

}
