package com.example.androiddemo.retrofit;


import android.os.Build;
import com.example.androiddemo.enumSet.RetrofitConverter;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

import java.util.ArrayList;
import java.util.List;

public class RetrofitConfig {

    private String BaseUrl = "";
    private int Read_Timeout = 10;
    private int Write_Timeout = 10;
    private int Conn_Timeout = 10;
    private List<Interceptor> interceptors = new ArrayList<>();

    private RetrofitConverter converterType = RetrofitConverter.GSON;
    private boolean hasRxAdapter = true;

    //可以直接传入 client和retrofit这样就不用传入其他默认类
    private OkHttpClient client = null;
    private Retrofit retrofit = null;

    private RetrofitConfig() {

    }

    public String getBaseUrl() {
        return BaseUrl;
    }

    public int getRead_Timeout() {
        return Read_Timeout;
    }

    public int getWrite_Timeout() {
        return Write_Timeout;
    }

    public int getConn_Timeout() {
        return Conn_Timeout;
    }

    public OkHttpClient getClient() {
        return client;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public List<Interceptor> getInterceptors() {
        return interceptors;
    }

    public RetrofitConverter getConverterType() {
        return converterType;
    }

    public boolean hasRxAdapter() {
        return hasRxAdapter;
    }

    public static class Builder {

        private static RetrofitConfig Instace = new RetrofitConfig();

        public Builder() {

        }

        public RetrofitConfig build() {
            return Instace;
        }

        public Builder setReadTimeout(int time) {
            Instace.Read_Timeout = time;
            return this;
        }

        public Builder setWriteTimeout(int time) {
            Instace.Write_Timeout = time;
            return this;
        }

        public Builder setCoonTimeout(int time) {
            Instace.Conn_Timeout = time;
            return this;
        }

        public Builder setBaseUrl(String url) {
            Instace.BaseUrl = url;
            return this;
        }

        public Builder setClient(OkHttpClient client) {
            Instace.client = client;
            return this;
        }

        public Builder setRetrofit(Retrofit retrofit) {
            Instace.retrofit = retrofit;
            return this;
        }

        public Builder addInterceptors(List<Interceptor> interceptors) {
            Instace.interceptors = interceptors;
            return this;
        }

        public Builder setConverter(RetrofitConverter converter) {
            Instace.converterType = converter;
            return this;
        }

        public Builder NoRxAdapter() {
            Instace.hasRxAdapter = false;
            return this;
        }

    }

}
