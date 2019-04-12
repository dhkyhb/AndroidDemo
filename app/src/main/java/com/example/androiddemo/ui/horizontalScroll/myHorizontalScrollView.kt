package com.example.androiddemo.ui.horizontalScroll

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.animation.TranslateAnimation
import android.widget.HorizontalScrollView

class myHorizontalScrollView(context: Context, attrs : AttributeSet?, defStyleAttr: Int) : HorizontalScrollView(context, attrs, defStyleAttr) {

    var inner: View? = null

    constructor(context : Context, attrs : AttributeSet?): this(context, attrs,0)

    constructor(context : Context): this(context, null)

    init {
        //取消滑动到最前和最后是出现的蓝色颜色阴影块
        overScrollMode = View.OVER_SCROLL_NEVER
    }


    override fun onFinishInflate() {
        super.onFinishInflate()
        if(childCount >0)
            inner=getChildAt(0)
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        if(inner!=null){
            commOnTouchEvent(ev!!)
        }
        return super.onTouchEvent(ev)
    }

    private var downX: Float? = 0.0f
    private val DEFAULT_DEVIDE = 4
    private var normal = Rect()
    private fun commOnTouchEvent(ev: MotionEvent){
        when (ev.action){
            MotionEvent.ACTION_DOWN -> downX=ev.x
            MotionEvent.ACTION_MOVE -> {
                var moveX = ev.x
                var deltX =(downX!! - moveX) as Int / DEFAULT_DEVIDE
                downX = moveX

                //不能滚动就直接移动布局
                if (isNeedMove()) {
                    if (normal.isEmpty) {
                        // 保存正常的布局位置
                        normal.set(
                            inner!!.left, inner!!.top,
                            inner!!.right, inner!!.bottom
                        )
                        return
                    }
                    inner!!.layout(inner!!.left - deltX, inner!!.top, inner!!.right - deltX, inner!!.bottom)
                }
            }
            MotionEvent.ACTION_UP -> {
                if (isNeedAnimation()) {
                    // Log.v("mlguitar", "will up and animation")
                    animation()
                }
            }
        }
    }

    private fun isNeedMove(): Boolean {
        var offset=inner!!.measuredWidth - width
        var scrollX= scrollX;
        Log.d("zbv", "offset=$offset;scrollX=$scrollX")
        //头和尾
        if(scrollX==0||scrollX==offset){
            return true
        }
        return false
    }

    // 开启动画移动
    public fun animation() {
        // 开启移动动画
        var ta = TranslateAnimation(
            left.toFloat(), normal.left.toFloat(), 0F,
            0F)
        ta.duration = 200
        inner!!.startAnimation(ta)
        // 设置回到正常的布局位
        inner!!.layout(normal.left, normal.top, normal.right, normal.bottom)
        normal.setEmpty()
    }

    // 是否需要开启动画
    public fun isNeedAnimation() : Boolean {
        return !normal.isEmpty
    }

}