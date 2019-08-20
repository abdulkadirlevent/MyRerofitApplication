/*
 * Created by Abdulkadir LEVENT  20.08.2019 03:34
 * Copyright (c) 2019 . All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * Last modified:  20.08.2019 02:17
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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import com.levent.myrerofitapplication.R;
import com.levent.myrerofitapplication.api.ApiInterface;
import com.levent.myrerofitapplication.api.NetworkService;
import com.levent.myrerofitapplication.model.Post;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostActivity extends AppCompatActivity {
    public static final String TAG = "PostActivity";

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.fab) FloatingActionButton fab;

    @BindView(R.id.textView_userId) TextView textView_userId;
    @BindView(R.id.textView_Id) TextView textView_Id;
    @BindView(R.id.textView_title) TextView textView_title;
    @BindView(R.id.textView_body) TextView textView_body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());

        Post post = (Post) getIntent().getSerializableExtra("POST_EXTRA");
        if (post != null) {
            setData(post);
        } else {
            /**
             * 12 ID NOLU DATA ALINACAK
             * .getPostID(12) Data ID yazınız
             */
            getData(12);
        }
    }

    /**
     * ÖRNEK 1
     * @param dataID
     */
    public void getData(int dataID) {
        ApiInterface service = NetworkService.getInstance().getJSONApi();
        Call<Post> call = service.getPostID(dataID);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Log.i(TAG, response.body().toString());
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i(TAG, "onSuccess:" + response.body().toString());
                        setData(response.body());
                    } else {
                        Log.i(TAG, "Kayıt bulunamadı!");
                        Toast.makeText(PostActivity.this, "Kayıt bulunamadı!", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.e(TAG, "Hata!" + t.getMessage());
                call.cancel();
                t.printStackTrace();
            }
        });
    }

    /**
     * ÖRNEK 2
     * @param dataID
     */
    public void getData2(int dataID) {
        NetworkService.getInstance()
                .getJSONApi()
                .getPostID(dataID)
                .enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        Log.i(TAG, response.body().toString());

                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                Log.i(TAG, "onSuccess:" + response.body().toString());
                                setData(response.body());
                            } else {
                                Log.i(TAG, "Kayıt bulunamadı!");
                                Toast.makeText(PostActivity.this, "Kayıt bulunamadı!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                        Log.e(TAG, "Hata!" + t.getMessage());
                        call.cancel();
                        t.printStackTrace();
                    }
                });
    }

    public void setData(Post post) {
        if (post != null) {
            textView_userId.setText(String.valueOf(post.getUserId()));
            textView_Id.setText(String.valueOf(post.getId()));
            textView_title.setText(post.getTitle());
            textView_body.setText(post.getBody());
        } else {
            Log.i(TAG, "Data NULL!");
            Toast.makeText(PostActivity.this, "Data NULL!", Toast.LENGTH_SHORT).show();
        }
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
