package com.example.baseproject.dagger.module;

import com.example.baseproject.activity.AuthenticateActivity;
import com.example.baseproject.fragments.LogInFragment;
import com.example.baseproject.fragments.ScheduleTaskFragment;
import com.example.baseproject.fragments.SignUpFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * created by Rishika Agarwal on 7/2/2019.
 */
@Module
public abstract class FragmentBuilderModule {
    @ContributesAndroidInjector
    abstract ScheduleTaskFragment scheduleTaskFragment();

    @ContributesAndroidInjector
    abstract LogInFragment logInFragment();

    @ContributesAndroidInjector
    abstract SignUpFragment signUpFragment();


}
