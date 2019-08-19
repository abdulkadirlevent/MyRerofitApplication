/*
 * Created by Abdulkadir LEVENT  19.08.2019 23:25
 * Copyright (c) 2019 . All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * Last modified:  19.08.2019 23:25
 * Contact: Email : abdulkadirlevent@hotmail.com
 * Package: app / MyRerofitApplication
 * Mobil Proje Yönetim Sistemleri
 */

package com.levent.myrerofitapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.levent.myrerofitapplication.R;
import com.levent.myrerofitapplication.activity.PostActivity;
import com.levent.myrerofitapplication.model.Post;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostsCardAdapter extends RecyclerView.Adapter<CarViewHolder> {
    private static final String TAG = PostsCardAdapter.class.getSimpleName();
    private Context context;
    private List<Post> postList;

    public PostsCardAdapter(Context context, List<Post> dataList) {
        this.postList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_posts_item, parent, false);
        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        Post post = postList.get(position);
        holder.textViewTitle.setText(post.getTitle());
        holder.textViewBody.setText(post.getBody());
        holder.textViewId.setText("User ID:" + post.getUserId() + "  ID:" + post.getId());
        holder.cardViewPost.setOnClickListener(view -> {
            /**
             * onClick
             */
            //Log.i(TAG, "ID:" + post.getId());
            //Toast.makeText(context, post.getTitle(), Toast.LENGTH_LONG).show();
            if (post != null) {
                Intent i = new Intent(context, PostActivity.class);
                i.putExtra("POST_EXTRA", post);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }
}

class CarViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.cardViewPost)  public CardView cardViewPost;
    @BindView(R.id.textViewTitle) public TextView textViewTitle;
    @BindView(R.id.textViewBody)  public TextView textViewBody;
    @BindView(R.id.textViewId)    public TextView textViewId;
    public CarViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}