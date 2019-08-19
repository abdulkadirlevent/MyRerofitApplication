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