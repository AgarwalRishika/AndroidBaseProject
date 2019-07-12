package com.example.baseproject.activity;

import  android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.baseproject.R;
import com.example.baseproject.common.AppConstant;
import com.example.baseproject.database.AppDataBase;

import dagger.android.AndroidInjection;
import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.textView)
    TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
