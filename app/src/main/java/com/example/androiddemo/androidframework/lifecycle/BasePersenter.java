package com.example.androiddemo.androidframework.lifecycle;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.util.Log;
import org.jetbrains.annotations.NotNull;

public class BasePersenter implements IPersenter {

    public static final String TAG = BasePersenter.class.getSimpleName();

    @Override
    public void init(@NotNull LifecycleOwner owner) {
        Log.e(TAG, "BasePersenter.onCreate()");

        Log.e(TAG, "call init()");
    }

    @Override
    public void onDestroy(@NotNull LifecycleOwner owner) {
        Log.e(TAG, "BasePersenter.onDestroy()");
    }

    @Override
    public void onLifecycleChanged(@NotNull LifecycleOwner owner, @NotNull Lifecycle.Event event) {
        Log.e(TAG, "BasePersenter."  + event.name());
    }
}
