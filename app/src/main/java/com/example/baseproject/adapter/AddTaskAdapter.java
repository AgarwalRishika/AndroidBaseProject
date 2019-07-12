package com.example.baseproject.adapter;

import androidx.annotation.NonNull;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;
import com.example.baseproject.R;
import com.example.baseproject.databinding.RowAddTaskBinding;

import java.util.ArrayList;

/**
 * created by Rishika Agarwal on 6/26/2019.
 */
public class AddTaskAdapter extends BaseAdapter {

    public AddTaskAdapter(ArrayList<String> arrayList){
        this.arrayList = arrayList;
    }


    public void setList(ArrayList<String> arrayList){
        this.arrayList = arrayList;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getLayout() {
        return R.layout.row_add_task;
    }

    @Override
    public int getBrVariable() {
        return BR.string;
    }}
