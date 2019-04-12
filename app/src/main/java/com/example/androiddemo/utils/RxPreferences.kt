package com.example.androiddemo.utils

import android.content.SharedPreferences
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import java.lang.IllegalArgumentException


class RxPreferences private constructor(sharedPreferences: SharedPreferences) {

    private val DEFAULT_FLOAT = 0f
    private val DEFAULT_INTEGER = 0
    private val DEFAULT_BOOLEAN = false
    private val DEFAULT_LONG = 0L
    private val DEFAULT_STRING = ""
    var sharedPreferences: SharedPreferences? = null
    var keyChanges: Observable<String>? = null

    init {
        this.sharedPreferences = sharedPreferences
        this.keyChanges = Observable.create(object : ObservableOnSubscribe<String> {
            override fun subscribe(emitter: ObservableEmitter<String>) {
                val listener =
                    SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, key ->
                        emitter.onNext(key)
                    }
                emitter.setCancellable { sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener) }

                sharedPreferences.registerOnSharedPreferenceChangeListener(listener)
            }
        }).share()
    }


    companion object {

        fun create(sharedPreferences: SharedPreferences): RxPreferences {
            checkNotNull(sharedPreferences,
                return RxPreferences(sharedPreferences))
        }
    }
}