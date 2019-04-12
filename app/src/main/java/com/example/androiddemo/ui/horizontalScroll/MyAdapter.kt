package com.example.androiddemo.ui.horizontalScroll

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.androiddemo.R
import com.example.androiddemo.utils.ToastUtils
import kotlinx.android.synthetic.main.item_horizontal_layout.view.*

class MyAdapter(context: Context, dataList: List<String>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    var context: Context? = null
    var dataList: List<String>? = null

    init {
        this.context = context
        this.dataList = dataList
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_horizontal_layout, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList!!.size
    }


    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.itemView.tv_result.text = dataList!![p1]
        p0.itemView.tv_result.setOnClickListener {
            ToastUtils.showToast("item view $p1 clicked")
        }
    }


    class ViewHolder(v: View) : RecyclerView.ViewHolder(v)
}