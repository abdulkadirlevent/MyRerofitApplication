/*
 * Created by Abdulkadir LEVENT  19.08.2019 22:32
 * Copyright (c) 2019 . All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * Last modified:  19.08.2019 22:05
 * Contact: Email : abdulkadirlevent@hotmail.com
 * Package: app / MyRerofitApplication
 * Mobil Proje Yönetim Sistemleri
 */

package com.levent.myrerofitapplication.activity;

import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
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
import com.levent.myrerofitapplication.api.NetworkService;
import com.levent.myrerofitapplication.model.Post;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostsActivity extends AppCompatActivity {
    public static final String TAG = "PostsActivity";

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.recyclerView_posts) RecyclerView recyclerView_posts;

    private PostsAdapter mPostsAdapter;

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
                t.printStackTrace();
            }
        });

    }

    /**
     *
     * @param usersList
     */
    private void loadDataList(List<Post> usersList) {
        mPostsAdapter = new PostsAdapter(usersList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(PostsActivity.this);
        recyclerView_posts.setLayoutManager(layoutManager);
        recyclerView_posts.setAdapter(mPostsAdapter);
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
