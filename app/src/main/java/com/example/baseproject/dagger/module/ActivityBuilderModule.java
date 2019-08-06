package com.example.baseproject.dagger.module;

import com.example.baseproject.activity.AuthenticateActivity;
import com.example.baseproject.activity.CreateTaskActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * created by Rishika Agarwal on 6/24/2019.
 */
@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract AuthenticateActivity authenticateActivity();


//    @ContributesAndroidInjector
//    abstract BaseActivity baseActivity();


    @ContributesAndroidInjector
    abstract CreateTaskActivity createTaskActivity();

}
