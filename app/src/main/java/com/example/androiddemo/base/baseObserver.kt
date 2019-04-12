package com.example.androiddemo.base

import io.reactivex.Observer
import io.reactivex.disposables.Disposable

open class baseObserver<T> : Observer<T>{
    override fun onComplete() {
        System.out.print("onComplete")
    }

    override fun onSubscribe(d: Disposable) {
        System.out.print("onSubscribe")
    }

    override fun onNext(t: T) {
        System.out.print("onNext")
    }

    override fun onError(e: Throwable) {
        System.out.print("onError")
    }
}