/*
 * Created by Abdulkadir LEVENT  19.08.2019 22:32
 * Copyright (c) 2019 . All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * Last modified:  19.08.2019 20:28
 * Contact: Email : abdulkadirlevent@hotmail.com
 * Package: app / MyRerofitApplication
 * Mobil Proje Yönetim Sistemleri
 */

package com.levent.myrerofitapplication.api;

import com.levent.myrerofitapplication.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {
    /**
     * Tüm veriler isteniyor
     * @return
     */
    @GET("/posts")
    Call<List<Post>> getAllPosts();

    /**
     * ID No ile veri alınıyor
     * @param id
     * @return
     */
    @GET("/posts/{id}")
    Call<Post> getPostID(@Path("id") int id);
}