package com.example.baseproject.activity;

import android.os.Bundle;
import android.widget.FrameLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.baseproject.R;
import com.example.baseproject.fragments.LogInFragment;
import com.example.baseproject.fragments.SignUpFragment;

public class AuthenticateActivity extends BaseActivity {
    LogInFragment logInFragment;
    SignUpFragment signUpFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        initListener();




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


        logInFragment = new LogInFragment();
        signUpFragment = new SignUpFragment();
        setFragment(logInFragment);

    }



    @Override
    public void onFragmentChange() {
        super.onFragmentChange();

        Fragment fragment = fragmentManager.findFragmentByTag(TAG);
        if (fragment instanceof SignUpFragment) {
            setFragment(signUpFragment);
        } else if (fragment instanceof LogInFragment) {
            setFragment(logInFragment);
        }


    }



}
