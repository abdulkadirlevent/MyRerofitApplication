/*
 * Created by Abdulkadir LEVENT  20.08.2019 03:34
 * Copyright (c) 2019 . All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * Last modified:  20.08.2019 00:21
 * Contact: Email : abdulkadirlevent@hotmail.com
 * Package: app / MyRerofitApplication
 * Mobil Proje Yönetim Sistemleri
 */

package com.levent.myrerofitapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.levent.myrerofitapplication.R;
import com.levent.myrerofitapplication.model.Post;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.CustomViewHolder> {
    private List<Post> dataList;

    public PostsAdapter(List<Post> dataList) {
        this.dataList = dataList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        public final View myView;
        TextView textUser;
        TextView textBody;
        TextView textViewId;

        CustomViewHolder(View itemView) {
            super(itemView);
            myView = itemView;
            textUser = myView.findViewById(R.id.textViewTitle);
            textBody = myView.findViewById(R.id.textViewBody);
            textViewId = myView.findViewById(R.id.textViewId);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_posts_item, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.textUser.setText(dataList.get(position).getTitle());
        holder.textBody.setText(dataList.get(position).getBody());
        holder.textViewId.setText("User ID:" + dataList.get(position).getUserId() + "  ID:" + dataList.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}