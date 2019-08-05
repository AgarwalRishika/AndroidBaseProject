package com.example.baseproject.fragments;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.example.baseproject.R;
import com.example.baseproject.activity.CreateTaskActivity;
import com.example.baseproject.adapter.TaskAdapter;
import com.example.baseproject.common.AppUtils;
import com.example.baseproject.databinding.FragmentScheduleTaskBinding;
import com.example.baseproject.models.TaskModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScheduleTaskFragment extends BaseFragment {

    private  Observer<List<TaskModel>> datetaskObserver;
    private Observer<List<TaskModel>> alltaskObserver;
    private FragmentScheduleTaskBinding fragmentBinding;
    private ArrayList<TaskModel> arrayList = new ArrayList<>();
    private TaskAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);
        fragmentBinding = (FragmentScheduleTaskBinding) binding;
        init();
        initListener();
        return v;
    }

    @Override
    public void initListener() {
        super.initListener();
         datetaskObserver = new Observer<List<TaskModel>>() {
            @Override
            public void onChanged(List<TaskModel> taskModels) {
                arrayList.clear();
                for (TaskModel taskModel : taskModels) {
                    arrayList.add(taskModel);
                }

                setRecyclerviewVisibility();
                adapter.notifyDataSetChanged();

            }
        };

        alltaskObserver = new Observer<List<TaskModel>>() {
            @Override
            public void onChanged(List<TaskModel> taskModels) {
                arrayList.clear();
                for (TaskModel taskModel : taskModels) {
                    arrayList.add(taskModel);
                }

                setRecyclerviewVisibility();
                adapter.notifyDataSetChanged();

            }
        };
        getTaskByDate(Calendar.getInstance());


    }

    @Override
    public void init() {
        super.init();
        fragmentBinding.fab.setOnClickListener(this::onClick);
        adapter = new TaskAdapter(arrayList);
        fragmentBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        fragmentBinding.recyclerView.setAdapter(adapter);
        initializeCalendarView();
        // Create the observer which updates the UI.


        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.


    }

    @Override
    int getLayout() {
        return R.layout.fragment_schedule_task;
    }


    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), CreateTaskActivity.class);
        startActivity(intent);
    }

    private void initializeCalendarView() {
        List<EventDay> events = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        events.add(new EventDay(calendar, R.drawable.wallpaper, Color.parseColor("#228B22")));
        Calendar min = Calendar.getInstance();
        fragmentBinding.calendarView.setMinimumDate(min);
        fragmentBinding.calendarView.setEvents(events);
        fragmentBinding.calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                getTaskByDate(eventDay.getCalendar());
//                Calendar clickedDayCalendar = eventDay.getCalend
            }
        });


//        You can use our utils method to create Drawable with text
//        CalendarUtils.getDrawableText(Context context, String text, Typeface typeface, int color, int size);
    }


    private void setRecyclerviewVisibility() {
        if (AppUtils.isListEmpty(arrayList)) {
            fragmentBinding.imgEmpty.setVisibility(View.VISIBLE);
            fragmentBinding.recyclerView.setVisibility(View.GONE);

        } else {
            fragmentBinding.imgEmpty.setVisibility(View.GONE);
            fragmentBinding.recyclerView.setVisibility(View.VISIBLE);

        }
    }


    private void getTaskByDate(Calendar calendar) {
//        Calendar calendar = fragmentBinding.calendarView.getFirstSelectedDate();
        long today = calendar.getTimeInMillis();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        long tomorrow = calendar.getTimeInMillis();

        taskRespository.getTasksByDate(today, tomorrow).observe(this, datetaskObserver);
    }


    private void getAllTask() {

        taskRespository.getTasks().observe(this, alltaskObserver);


    }

}
