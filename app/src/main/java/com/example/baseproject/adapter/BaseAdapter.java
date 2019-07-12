package com.example.baseproject.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * created by Rishika Agarwal on 6/26/2019.
 */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter<ViewHolder> {
    ArrayList<T> arrayList;

    ViewDataBinding binding;
    ViewHolder holder;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      binding =  DataBindingUtil.inflate( LayoutInflater.from(parent.getContext()),getLayout() , parent , false);
        holder =  new ViewHolder(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getBrVariable(),arrayList.get(position));

    }
    public  abstract int getLayout();
    public abstract int getBrVariable();

    @Override
    public int getItemCount() {
        return arrayList.size();
                //arrayList.size();
    }






}
