package com.example.baseproject.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.baseproject.R;
import com.example.baseproject.activity.MainActivity;
import com.example.baseproject.common.AppUtils;
import com.example.baseproject.common.UIUtils;
import com.example.baseproject.databinding.FragmentLogInBinding;
import com.example.baseproject.interfaces.FirebaseOperationListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import javax.inject.Inject;


public class LogInFragment extends BaseFragment implements FirebaseOperationListener {

    FragmentLogInBinding fragmentBinding;
    @Inject
    FirebaseAuth mAuth;
    private String TAG = getClass().getSimpleName();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);
        fragmentBinding = (FragmentLogInBinding) binding;
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

    public void showSignUp(View v) {
        listener.onFragmentChange();
    }


    public void logIn(View v) {
        AppUtils.firebaseLogInEmailPass(mAuth, fragmentBinding.etUserName.getText().toString(), fragmentBinding.etPassword.getText().toString(), getActivity(), this);


    }


    public void onClick(View v) {

    }

    @Override
    public void firebaseOperationCompleted(Object o, Class c) {
        if (c.equals(FirebaseAuth.class)) {
            AppUtils.startActivity(getActivity(), MainActivity.class);
            getActivity().finish();
        } else if (c.equals(FirebaseFirestore.class)) {

        }

    }

    @Override
    public void firebaseOperationFailed(Exception e, Class c) {
        UIUtils.showToast(getActivity(), e.toString());
        UIUtils.showLog(TAG , e.toString());
        if (c.equals(FirebaseAuth.class)) {

        } else if (c.equals(FirebaseFirestore.class)) {

        }
    }

    @Override
    public void firebaseOperationSuccess(Object o, Class c) {

    }

//    @Override
//    public void firebaseOperationComplete(Object o, Class c) {
//
//    }


}
