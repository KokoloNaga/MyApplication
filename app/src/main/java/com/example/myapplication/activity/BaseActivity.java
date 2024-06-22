package com.example.myapplication.activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;
/*
 对于某个Activity，通过继承BaseActivity，可以调用它的finishAllActivity参数，从而销毁所有Activity
 */
public class BaseActivity extends AppCompatActivity {
    private FinishApplication myApplication;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(myApplication == null){
            myApplication = (FinishApplication) getApplication();  // 当前应用获取
            myApplication.addActivity(BaseActivity.this);
        }


    }
    public void finishAllActivity(){
        myApplication.finishAllActivity();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        myApplication.removeActivity(BaseActivity.this);
    }
}