package com.example.baseproject.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.example.baseproject.R;
import com.example.baseproject.activity.BaseActivity;
import com.example.baseproject.database.AppDataBase;
import com.example.baseproject.database.TaskRespository;
import com.example.baseproject.interfaces.AfterDbOperationListener;
import com.example.baseproject.interfaces.FragmentChangeListener;
import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.DaggerFragment;

import javax.inject.Inject;

/**
 * created by Rishika Agarwal on 6/24/2019.
 */
public abstract class BaseFragment extends DaggerFragment  {

    FragmentChangeListener listener;

    ViewDataBinding binding;
    @Inject
    TaskRespository taskRespository;




    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (BaseActivity)context;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater , getLayout() , container , false);

//                inflater.inflate(getLayout(), container, false);
        return binding.getRoot();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }


     public void init(){

     }

     public void initListener(){

     }
    abstract int  getLayout();
}
