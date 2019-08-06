package com.example.baseproject.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.example.baseproject.R;
import com.example.baseproject.activity.MainActivity;
import com.example.baseproject.common.AppConstant;
import com.example.baseproject.common.AppUtils;
import com.example.baseproject.databinding.FragmentSignUpBinding;
import com.example.baseproject.interfaces.FirebaseOperationListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

import com.example.baseproject.common.AppConstant;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends BaseFragment implements FirebaseOperationListener {

    FragmentSignUpBinding fragmentBinding;
    @Inject
    FirebaseAuth mAuth;
    @Inject
    FirebaseFirestore firebaseFirestore;

    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);
        fragmentBinding = (FragmentSignUpBinding) binding;
        init();
        initListener();
        return v;
    }

    @Override
    int getLayout() {
        return R.layout.fragment_sign_up;
    }

    @Override
    public void init() {
        super.init();


    }

    @Override
    public void initListener() {
        super.initListener();
        fragmentBinding.signUp.setOnClickListener(this::signUp);
        fragmentBinding.logIn.setOnClickListener(this::showLogIn);
    }

    public void signUp(View v) {
        AppUtils.firebaseSignUpEmailPass(mAuth, fragmentBinding.etEmail.getText().toString(), fragmentBinding.etPassword.getText().toString(), getActivity(), this);
    }


    public void showLogIn(View v) {

        listener.onFragmentChange();

    }


    @Override
    public void firebaseOperationCompleted(Object o, Class c) {


    }

    @Override
    public void firebaseOperationFailed(Object o, Class c) {
        if (c.equals(FirebaseAuth.class)) {

        } else if (c.equals(FirebaseFirestore.class)) {

        }
    }

    @Override
    public void firebaseOperationSuccess(Object o, Class c) {
        if (c.equals(FirebaseAuth.class)) {
            Map<String, String> map = new HashMap<>();
            map.put(AppConstant.EMAIL, fragmentBinding.etEmail.getText().toString());
            map.put(AppConstant.PASSWORD, fragmentBinding.etPassword.getText().toString());
            map.put(AppConstant.NAME, fragmentBinding.etUserName.getText().toString());
            map.put(AppConstant.MOBILENO, fragmentBinding.etMbl.getText().toString());
            AppUtils.saveDataToFireStore(map, firebaseFirestore, getActivity(), fragmentBinding.etEmail.getText().toString(), this);


//
        } else if (c.equals(FirebaseFirestore.class)) {
            AppUtils.startActivity(getActivity(), MainActivity.class);
        }
    }


}
