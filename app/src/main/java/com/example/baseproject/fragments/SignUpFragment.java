package com.example.baseproject.fragments;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.OnClick;
import com.example.baseproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends BaseFragment {


    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    View v =   super.onCreateView(inflater ,container , savedInstanceState);
    return v;
    }

    @Override
    int getLayout() {
        return R.layout.fragment_sign_up;
    }


    @OnClick(R.id.logIn)
    public void showLogIn(){
        listener.onFragmentChange();
    }

    @OnClick(R.id.signUp)
    public void signUp(){

    }



    public void onClick(View v) {

    }
}
