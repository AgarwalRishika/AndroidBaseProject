package com.example.baseproject.dagger.component;

import android.app.Application;
import com.example.baseproject.MyApplication;
import com.example.baseproject.dagger.module.ActivityBuilderModule;
import com.example.baseproject.dagger.module.AppModule;
import com.example.baseproject.dagger.module.FragmentBuilderModule;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

import javax.inject.Singleton;

/**
 * created by Rishika Agarwal on 6/24/2019.
 */
@Singleton
@Component(modules = {AndroidSupportInjectionModule.class , ActivityBuilderModule.class , AppModule.class , FragmentBuilderModule.class})
public interface AppComponent extends AndroidInjector<MyApplication> {

    @Component.Builder
    interface  Builder{
        AppComponent build();

        @BindsInstance
        Builder application(Application application);
    }


}

    /**
     *  @BindInstance of Application to the component at the time of its creation
      */
