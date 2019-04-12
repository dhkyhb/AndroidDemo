package com.example.androiddemo.ui.horizontalScroll

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.androiddemo.R
import com.example.androiddemo.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_horizontal_scroll.*
import org.jetbrains.anko.toast

class HorizontalScrollActivity : AppCompatActivity() {

    var lastDecoration : RecyclerView.ItemDecoration? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horizontal_scroll)
        var adapter = MyAdapter(this, arrayListOf("劳资最帅", "你们技术最菜", "劳资最帅", "滚蛋吧你", "面对疾风吧"
            , "德莱文为队友争取了宝贵的时间", "写不下去了", "德玛西亚", "契约~~", "劳资最帅", "你们技术最菜", "劳资最帅", "滚蛋吧你", "面对疾风吧"
            , "德莱文为队友争取了宝贵的时间", "写不下去了", "德玛西亚", "契约~~", "劳资最帅", "你们技术最菜", "劳资最帅", "滚蛋吧你", "面对疾风吧"
            , "德莱文为队友争取了宝贵的时间", "写不下去了", "德玛西亚", "契约~~", "劳资最帅", "你们技术最菜", "劳资最帅", "滚蛋吧你", "面对疾风吧"
            , "德莱文为队友争取了宝贵的时间", "写不下去了", "德玛西亚", "契约~~"))
//        var layoutManager = LinearLayoutManager(this)
//        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
//        rv_demo.removeItemDecoration(decoration)
//        var decoration = PagingItemDecoration(this, horizonpageLayoutManager)
//        rv_demo.addItemDecoration(decoration)
        var horizonpageLayoutManager = HorizontalPageLayoutManager(2, 4)
        var scrollHelper = PagingScrollHelper()
        rv_demo.layoutManager = horizonpageLayoutManager
        rv_demo.adapter = adapter
        rv_demo.isHorizontalScrollBarEnabled = true
        scrollHelper.setUpRecycleView(rv_demo)
        scrollHelper.setOnPageChangeListener {
            ToastUtils.showToast("当前pageCount:$it")
        }
        scrollHelper.updateLayoutManger()
        scrollHelper.scrollToPosition(0)
    }
}
