package com.example.baseproject.dagger.module;

import android.app.Application;
import androidx.room.Room;
import com.example.baseproject.common.AppConstant;
import com.example.baseproject.database.AppDataBase;
import com.example.baseproject.database.TaskRespository;
import com.example.baseproject.network.RetrofitService;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

import javax.inject.Singleton;

/**
 * created by Rishika Agarwal on 6/24/2019.
 */
@Module
public class AppModule {

    @Provides
    static boolean getApp(Application application) {
        return application == null;
    }

    @Provides
    @Singleton
    static Retrofit retrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConstant.BASE_URL).build();

        return retrofit;
    }

    @Provides
    static RetrofitService retrofitService(Retrofit retrofit) {
        return retrofit.create(RetrofitService.class);
    }


    @Provides
    @Singleton
    static AppDataBase getAppDataBase(Application application){
        return Room.databaseBuilder(application,
                AppDataBase.class,
                AppConstant.DATABASE_NME)
                .build();
    }


//    @Provides
//    static TaskRespository getTaskRepository(){
//        return new TaskRespository();
//    }

}
