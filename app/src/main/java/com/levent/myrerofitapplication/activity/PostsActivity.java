/*
 * Created by Abdulkadir LEVENT  20.08.2019 03:34
 * Copyright (c) 2019 . All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * Last modified:  20.08.2019 01:13
 * Contact: Email : abdulkadirlevent@hotmail.com
 * Package: app / MyRerofitApplication
 * Mobil Proje Yönetim Sistemleri
 */

package com.levent.myrerofitapplication.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.levent.myrerofitapplication.R;
import com.levent.myrerofitapplication.adapter.PostsAdapter;
import com.levent.myrerofitapplication.adapter.PostsCardAdapter;
import com.levent.myrerofitapplication.api.NetworkService;
import com.levent.myrerofitapplication.model.Post;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostsActivity extends AppCompatActivity  {
    public static final String TAG = "PostsActivity";

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.recyclerView_posts) RecyclerView recyclerView_posts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NetworkService.getInstance()
                .getJSONApi()
                .getAllPosts()
                .enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                Log.i(TAG, response.body().toString());

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i(TAG, "onSuccess:"+ response.body().toString());
                        loadDataList(response.body());
                    } else {
                        Log.i(TAG, "Kayıt bulunamadı!");
                        Toast.makeText(PostsActivity.this, "Kayıt bulunamadı!", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.i(TAG, "Hata!" + t.getMessage());
                call.cancel();
                t.printStackTrace();
            }
        });

    }

    /**
     *
     * @param usersList
     */
    private void loadDataList(List<Post> usersList) {
        /**
         * Bunları sırayla dene
         */
        //PostsAdapter mPostsAdapter = new PostsAdapter(usersList);
        //PostsCardAdapter mPostsAdapter = new PostsCardAdapter(PostsActivity.this, getCustomData());
        PostsCardAdapter mPostsAdapter = new PostsCardAdapter(PostsActivity.this, usersList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(PostsActivity.this);
        recyclerView_posts.setLayoutManager(layoutManager);
        recyclerView_posts.setAdapter(mPostsAdapter);
    }

    /**
     * Verileri manuel eklemek istersen kullan
     * @return
     */
    public List<Post> getCustomData() {
        List<Post> post = new ArrayList<Post>();
        post.add(new Post(1,1,"Ferrari", "mesaj 1"));
        post.add(new Post(1,2,"Bugatti", "mesaj 2"));
        post.add(new Post(2,3,"Bentley", "mesaj 3"));
        post.add(new Post(2,4,"Porsche", "mesaj 4"));
        post.add(new Post(3,5,"Mercedes", "mesaj 5"));
        post.add(new Post(3,6,"Audi", "mesaj 6"));
        post.add(new Post(4,7,"Bmv", "mesaj 7"));
        post.add(new Post(4,8,"Passat", "mesaj 8"));
        post.add(new Post(5,9,"Seat", "mesaj 9"));
        post.add(new Post(5,10,"Citroen", "mesaj 10"));
        return post;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_post, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
