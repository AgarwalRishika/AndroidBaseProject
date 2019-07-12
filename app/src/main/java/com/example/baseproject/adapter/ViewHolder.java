package com.example.baseproject.adapter;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;

/**
 * created by Rishika Agarwal on 7/8/2019.
 */
public class ViewHolder<T> extends RecyclerView.ViewHolder {
    ViewDataBinding binding;
    public ViewHolder(@NonNull ViewDataBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(int variable , T item) {
        binding.setVariable(variable, item);
        binding.executePendingBindings();
    }
}