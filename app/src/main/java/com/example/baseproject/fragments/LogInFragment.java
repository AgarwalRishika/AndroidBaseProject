package com.example.baseproject.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.OnClick;
import com.example.baseproject.R;
import com.example.baseproject.activity.NavigationBarActivity;
import com.example.baseproject.common.AppUtils;
import com.example.baseproject.databinding.FragmentLogInBinding;


public class LogInFragment extends BaseFragment {

    FragmentLogInBinding fragmentBinding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =   super.onCreateView(inflater ,container , savedInstanceState);
        fragmentBinding = (FragmentLogInBinding)binding;
        init();
        initListener();
        return v;
    }

    @Override
    int getLayout() {
        return R.layout.fragment_log_in;
    }

    @Override
    public void init() {
        super.init();


    }

    @Override
    public void initListener() {
        super.initListener();
        fragmentBinding.signUp.setOnClickListener(this::showSignUp);
        fragmentBinding.logIn.setOnClickListener(this::logIn);
    }

    public void showSignUp(View v){
        listener.onFragmentChange();
    }


    public void logIn(View v){
        AppUtils.startActivity(getActivity() , NavigationBarActivity.class);

    }



    public void onClick(View v) {

    }
}
