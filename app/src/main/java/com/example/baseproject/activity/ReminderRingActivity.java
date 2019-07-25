package com.example.baseproject.activity;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.baseproject.R;
import com.example.baseproject.common.AppConstant;
import com.example.baseproject.databinding.ActivityReminderRingBinding;
import com.example.baseproject.interfaces.AfterDbOperationListener;

public class ReminderRingActivity extends BaseActivity implements AfterDbOperationListener {

    ActivityReminderRingBinding activityBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_ring);
        init();
        initListener();
    }

    @Override
    int getLayout() {
        return R.layout.activity_reminder_ring;
    }

    @Override
    void init() {
        super.init();
        activityBinding = (ActivityReminderRingBinding)binding;
        activityBinding.btnDismiss.setOnClickListener(this::dismissAlarm);
        taskRespository.setListener(this);
    }

   private void dismissAlarm(View v){
       Intent intent = new Intent();
       int id = intent.getIntExtra(AppConstant.INTENT_ID,-1);
       taskRespository.deleteTask(id);

   }

    @Override
    void initListener() {
        super.initListener();
    }

    @Override
    public void dbOperationCompleted(Object o) {
        finish();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
