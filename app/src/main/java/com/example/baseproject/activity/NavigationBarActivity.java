package com.example.baseproject.activity;

import android.view.MenuItem;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.baseproject.R;
import com.example.baseproject.databinding.ActivityNavigationBarBinding;
import com.example.baseproject.fragments.LogInFragment;
import com.example.baseproject.fragments.ScheduleTaskFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class NavigationBarActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    ActivityNavigationBarBinding activityBinding;

    String TAG = "FRAGMENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_navigation_bar);
        init();


    }

    @Override
    int getLayout() {
        return R.layout.activity_navigation_bar;
    }

    @Override
    void init() {
        activityBinding = (ActivityNavigationBarBinding) binding;

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
