package com.example.androiddemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public abstract class BaseMvpActivity<T extends BasePresneter> extends AppCompatActivity {

    protected T presneter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        presneter = createPresenter();
        super.onCreate(savedInstanceState);
    }

    protected abstract T createPresenter();

}
