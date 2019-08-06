package com.example.baseproject.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.NonNull;
import com.example.baseproject.R;
import com.example.baseproject.databinding.ActivityMainBinding;
import com.example.baseproject.fragments.ScheduleTaskFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    ActivityMainBinding activityBinding;

    String TAG = "FRAGMENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_navigation_bar);
        init();


    }

    @Override
    int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    void init() {
        activityBinding = (ActivityMainBinding) binding;

        activityBinding.bottomNavigationView.setVisibility(View.GONE);
        activityBinding.bottomNavigationView.setOnNavigationItemSelectedListener(this);
        setFragment(new ScheduleTaskFragment());


    }


    @Override
    public void onFragmentChange() {
        super.onFragmentChange();


    }

    public void showCategoryFragment(MenuItem item) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        setFragment(new ScheduleTaskFragment());
        return true;
    }
}
