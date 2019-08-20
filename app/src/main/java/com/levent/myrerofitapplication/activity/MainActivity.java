/*
 * Created by Abdulkadir LEVENT  20.08.2019 03:34
 * Copyright (c) 2019 . All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * Last modified:  20.08.2019 03:28
 * Contact: Email : abdulkadirlevent@hotmail.com
 * Package: app / MyRerofitApplication
 * Mobil Proje Yönetim Sistemleri
 */

package com.levent.myrerofitapplication.activity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.levent.myrerofitapplication.R;
import com.levent.myrerofitapplication.api.ApiInterface;
import com.levent.myrerofitapplication.api.NetworkService;
import com.levent.myrerofitapplication.model.Post;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.fab) FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    /**
     * Tek veri
     * Post Activity
     */
    @OnClick(R.id.button_obje_al) void objeAl() {
        Intent intent = new Intent(this, PostActivity.class);
        startActivity(intent);
    }

    /**
     * Tüm Veri listesi
     * Posts Activity
     */
    @OnClick(R.id.button_liste_al) void listeAl() {
        Intent intent = new Intent(this, PostsActivity.class);
        startActivity(intent);
    }

    /**
     * 12 ID Nolu kaydı Sil
     */
    @OnClick(R.id.button_delete) void postDelete() {
        deleteData(1);
    }

    /**
     * Yeni Post Olustur
     */
    @OnClick(R.id.button_create) void postCreate() {
        // Yeni Post Olusturuyoruz
        Post post = new Post(98, 99, "Merhaba bu yeni bir baslıktır", " buda yeni bir içeriktir.");
        createPostData(post);
    }

    /**
     * Post Güncelle
     */
    @OnClick(R.id.button_update) void postUpdate() {
        // Yeni Post verisi Olusturuyoruz
        Post post = new Post(1, 12, "Merhaba bu post guncellenmiştir", " bu içerikte güncellenmiştir.");
        updatePostData(post, post.getId());
    }

    /**
     * Post Güncelle
     * @param post
     */
    public void updatePostData(Post post, int id) {
        ApiInterface service = NetworkService.getInstance().getJSONApi();
        Call<Post> deletePost = service.updatePost(post,id);
        deletePost.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Log.i(TAG, response.toString());
                if (response.isSuccessful()) {
                    // Başarılı oldu ise Güncellediğimiz post serverden geri dönmelidir.
                    Post post = response.body();

                    Toast.makeText(MainActivity.this,
                            "Başarıyla Güncellendi:\n" + post.getTitle() +"  ID:"+ post.getId(),
                            Toast.LENGTH_LONG)
                            .show();
                } else {
                    Toast.makeText(MainActivity.this, "Hata! " + response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.e(TAG, "Hata!" + t.getMessage());
                t.printStackTrace();
            }
        });
    }

    /**
     * Yeni Post Olustur
     */
    public void createPostData(Post post) {
        ApiInterface service = NetworkService.getInstance().getJSONApi();
        Call<Post> deletePost = service.createPost(post);
        deletePost.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Log.i(TAG, response.body().toString());
                if (response.isSuccessful()) {
                    // Başarılı oldu ise oluşturduğumuz post serverden geri dönmelidir.
                    Post post = response.body();

                    Toast.makeText(MainActivity.this,
                            "Başarıyla olusturuldu:\n" + post.getTitle() +"  ID:"+ post.getId(),
                            Toast.LENGTH_LONG)
                            .show();
                } else {
                    Toast.makeText(MainActivity.this, "Hata! " + response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.e(TAG, "Hata!" + t.getMessage());
                t.printStackTrace();
            }
        });
    }

    /**
     * dataID Nolu kaydı sil
     * @param dataID
     */
    public void deleteData(int dataID) {
        ApiInterface service = NetworkService.getInstance().getJSONApi();
        Call<Post> deletePost = service.deletePost(dataID);
        deletePost.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post>call, Response<Post> response) {
                Log.i(TAG, response.toString());
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this,
                            "Başarıyla silindi\nKodumuz doğru çalışıyor.\nSilindiğine dair onay mesajı aldık\nAslında veri siilinmedi çünkü silme yetkimiz yok!",
                            Toast.LENGTH_LONG)
                            .show();
                }else{
                    Toast.makeText(MainActivity.this, "Hata! " + response.code(), Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Post>call, Throwable t) {
                Log.e(TAG, "Hata!" + t.getMessage());
                t.printStackTrace();
            }
        });
    }

}
