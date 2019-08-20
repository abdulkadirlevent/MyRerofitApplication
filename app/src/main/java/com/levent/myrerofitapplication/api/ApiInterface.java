/*
 * Created by Abdulkadir LEVENT  20.08.2019 03:34
 * Copyright (c) 2019 . All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * Last modified:  20.08.2019 03:21
 * Contact: Email : abdulkadirlevent@hotmail.com
 * Package: app / MyRerofitApplication
 * Mobil Proje Yönetim Sistemleri
 */

package com.levent.myrerofitapplication.api;

import com.levent.myrerofitapplication.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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

    /**
     * Yeni post oluşturuyoruz
     * @param post
     * @return
     */
    @POST("/posts")
    Call<Post> createPost(@Body Post post);

    /**
     * in nolu postu güncelliyoruz
     * @param post
     * @return
     */
    @PUT("/posts/{id}")
    Call<Post> updatePost(@Body Post post, @Path("id") int id);

    /**
     * id nolu postu siiliyoruz
     * @param postId
     * @return
     */
    @DELETE("/posts/{id}")
    Call<Post> deletePost(@Path("id") int postId);


}