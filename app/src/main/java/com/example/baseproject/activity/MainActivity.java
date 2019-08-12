package com.example.baseproject.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;
import com.example.baseproject.R;
import com.example.baseproject.common.AppUtils;
import com.example.baseproject.databinding.ActivityMainBinding;
import com.example.baseproject.fragments.ScheduleTaskFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    ActivityMainBinding activityBinding;
    ActionBarDrawerToggle t;

    String TAG = "FRAGMENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_navigation_bar);
        init();
        initListener();


    }

    @Override
    int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    void init() {
        activityBinding = (ActivityMainBinding) binding;

        activityBinding.bottomNavigationView.setVisibility(View.GONE);
        t = new ActionBarDrawerToggle(this, activityBinding.navDrawer,activityBinding.toolBar,R.string.Open, R.string.Close);
        activityBinding.navDrawer.addDrawerListener(t);
        t.syncState();
        initToolbar();
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);





    }

    private void initToolbar() {
        setSupportActionBar(activityBinding.toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
////        activityBinding.toolBar.setNavigationOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                finish();
////            }
////        });
//        getSupportActionBar().setTitle(TAG);
    }

    @Override
    void initListener() {
        super.initListener();
        activityBinding.navDrawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        activityBinding.navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.nav_log_out:
                        Toast.makeText(MainActivity.this, "Log Out",Toast.LENGTH_SHORT).show();
                    AppUtils.signOut();
                    AppUtils.startActivity(MainActivity.this , AuthenticateActivity.class);
                    finish();
                    break;
                    default:
                        return true;
                }


                return true;

            }
        });
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
