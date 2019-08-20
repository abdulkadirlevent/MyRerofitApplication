/*
 * Created by Abdulkadir LEVENT  20.08.2019 03:34
 * Copyright (c) 2019 . All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * Last modified:  19.08.2019 22:33
 * Contact: Email : abdulkadirlevent@hotmail.com
 * Package: app / MyRerofitApplication
 * Mobil Proje Yönetim Sistemleri
 */

package com.levent.myrerofitapplication.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    private static NetworkService mInstance;
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private Retrofit mRetrofit;

    /**
     *
     */
    private NetworkService() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .addInterceptor(interceptor);

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build();
    }

    /**
     *
     * @return
     */
    public static NetworkService getInstance() {
        if (mInstance == null) {
            mInstance = new NetworkService();
        }
        return mInstance;
    }

    /**
     *
     * @return
     */
    public ApiInterface getJSONApi() {
        return mRetrofit.create(ApiInterface.class);
    }
}
