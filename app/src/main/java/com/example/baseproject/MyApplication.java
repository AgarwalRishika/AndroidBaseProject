package com.example.baseproject;


import com.example.baseproject.dagger.component.DaggerAppComponent;
import com.facebook.stetho.Stetho;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * created by Rishika Agarwal on 6/24/2019.
 */
public class MyApplication extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
//        RoomDB.getInstance(this);
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }
}
