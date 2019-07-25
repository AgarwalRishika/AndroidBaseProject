package com.example.baseproject.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import androidx.annotation.Nullable;
import com.example.baseproject.activity.ReminderRingActivity;
import com.example.baseproject.common.AppConstant;

/**
 * created by Rishika Agarwal on 7/15/2019.
 */
public class MyService extends Service{


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Intent i = new Intent(this , ReminderRingActivity.class);
        i.putExtra(AppConstant.INTENT_ID, intent.getIntExtra(AppConstant.INTENT_ID , -1));
        getApplicationContext().startActivity(i);


        return  super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}