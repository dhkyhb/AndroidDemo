package com.example.androiddemo.extension

import android.util.Log
import com.example.androiddemo.base.baseObserver
import com.example.androiddemo.services.Result
import com.example.androiddemo.services.resultBean
import com.example.androiddemo.ui.WorkNetActivity
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_work_net.*


fun <T> Observable<T>.doInBackground(observer: baseObserver<T>) {
    this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(observer)
}
