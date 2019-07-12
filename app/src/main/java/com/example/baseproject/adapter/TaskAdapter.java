package com.example.baseproject.adapter;

import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;
import com.example.baseproject.R;
import com.example.baseproject.models.TaskModel;

import java.util.ArrayList;


/**
 * created by Rishika Agarwal on 6/26/2019.
 */
public class TaskAdapter extends BaseAdapter {


    public TaskAdapter(ArrayList<TaskModel> arrayList){
        this.arrayList = arrayList;
    }


    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getLayout() {
        return R.layout.row_schedule_task;
    }

    @Override
    public int getBrVariable() {
        return BR.task;
    }
}
