package com.example.baseproject.activity;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import com.example.baseproject.R;
import com.example.baseproject.common.AppUtils;
import com.example.baseproject.fragments.LogInFragment;
import com.example.baseproject.fragments.SignUpFragment;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

public class AuthenticateActivity extends BaseActivity {
    LogInFragment logInFragment;
    SignUpFragment signUpFragment;
    @Inject
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();



    }

    @Override
    int getLayout() {
        return R.layout.activity_authenticate;
    }

    @Override
    void initListener() {
        super.initListener();
    }

    @Override
    void init() {
        super.init();

        if (AppUtils.isUserLogIn(mAuth)) {
            AppUtils.startActivity(this , MainActivity.class);
            finish();
        } else {
            logInFragment = new LogInFragment();
            signUpFragment = new SignUpFragment();
            setFragment(logInFragment);
            initListener();
        }


    }


    @Override
    public void onFragmentChange() {
        super.onFragmentChange();

        Fragment fragment = fragmentManager.findFragmentByTag(TAG);
        if (fragment instanceof SignUpFragment) {
            setFragment(logInFragment);
        } else if (fragment instanceof LogInFragment) {
            setFragment(signUpFragment);
        }


    }


}
