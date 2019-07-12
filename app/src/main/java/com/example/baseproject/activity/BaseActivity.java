package com.example.baseproject.activity;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.baseproject.R;
import com.example.baseproject.database.AppDataBase;
import com.example.baseproject.database.TaskRespository;
import com.example.baseproject.interfaces.AfterDbOperationListener;
import com.example.baseproject.interfaces.FragmentChangeListener;
import com.example.baseproject.network.RetrofitService;
import dagger.android.support.DaggerAppCompatActivity;
import retrofit2.Retrofit;

import javax.inject.Inject;

/**
 * created by Rishika Agarwal on 6/24/2019.
 */
public abstract class BaseActivity extends DaggerAppCompatActivity implements FragmentChangeListener  {


    @Inject
    Retrofit retrofit;
    @Inject
    RetrofitService retrofitService;
    @Inject
    TaskRespository taskRespository;
    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;
    String TAG = "FRAGMENT";
    ViewDataBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, getLayout());

    }


    void init() {

//        AppDataBase.getAppDatabase(getApplicationContext());
//        appDatabase = Room.databaseBuilder(getApplicationContext(),
//                AppDatabase.class, AppConstant.DATABASE_NME).build();

    }

    void initListener() {

    }

    abstract int getLayout();

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onFragmentChange() {

    }

    public void setFragment(Fragment fragment) {
        if (fragmentManager == null) {
            fragmentManager = getSupportFragmentManager();
        }
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment, TAG);
        fragmentTransaction.commit();

    }
}