package com.example.androiddemo.retrofit;

import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import com.example.androiddemo.extension.LogKt;
import com.example.androiddemo.utils.RxjavaManager;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * created by dhkyhb 2019/4/20
 * Description:
 * https://github.com/square/retrofit/wiki/Converters
 */
public class RetrofitManager {

    private OkHttpClient client;
    private OkHttpClient.Builder clientBuilder;
    private static List<Interceptor> interceptorList;
    private Retrofit retrofit;
    private Retrofit.Builder retrofitBuilder;
    private String baseUrl;

    private RetrofitConfig config;

    private RetrofitManager() {
        init();
    }

    public static RetrofitManager getInstace() {
        return Builder.instance;
    }

    private void init() {
        clientBuilder = new OkHttpClient.Builder();

        retrofitBuilder = new Retrofit.Builder();
    }


    /**
     * @param config 配置属性类 可不添加 生成默认
     * @return
     */
    public RetrofitManager setConfig(RetrofitConfig config) {
        this.config = config;
        return this;
    }

    private RetrofitManager addConfig() {
        if (config == null) {
            LogKt.logInfo(RetrofitManager.class.getSimpleName(), "RetrofitConfig is null");
            config = new RetrofitConfig.Builder().build();
        }
        if (config.getRetrofit() != null) {
            this.retrofit = config.getRetrofit();
            return this;
        }
        if (config.getClient() != null) {
            this.client = config.getClient();
        } else {
            clientBuilder
                    .writeTimeout(config.getWrite_Timeout(), TimeUnit.SECONDS)
                    .connectTimeout(config.getConn_Timeout(), TimeUnit.SECONDS)
                    .readTimeout(config.getRead_Timeout(), TimeUnit.SECONDS);
        }
        retrofitBuilder.baseUrl(config.getBaseUrl());
        return this;
    }

    private RetrofitManager judge() {
        if (config.hasRxAdapter()) {
            retrofitBuilder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        }
        for (Interceptor interceptor : config.getInterceptors()){
            clientBuilder.addInterceptor(interceptor);
        }
        switch (config.getConverterType()) {
            case GSON:
                retrofitBuilder.addConverterFactory(GsonConverterFactory.create());
                break;
            case FASTJSON:
                retrofitBuilder.addConverterFactory(FastJsonConverterFactory.create());
                break;
            case JACKSON:
                retrofitBuilder.addConverterFactory(JacksonConverterFactory.create());
                break;
        }
        return this;
    }

    public <T> T createaService(Class<T> services) {
        addConfig();
        judge();

        //构建retrofit
        client = clientBuilder.build();
        retrofit = retrofitBuilder.client(client).build();
        return retrofit.create(services);
    }


    public static class Builder {
        private static RetrofitManager instance = new RetrofitManager();
    }

}
