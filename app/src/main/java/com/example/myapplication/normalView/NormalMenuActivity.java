package com.example.myapplication.normalView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

public class NormalMenuActivity extends AppCompatActivity {
    public Button mBtnTextView, mBtnRadioCheck, mBtnProgress, mBtnAlertPopup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_normal_menu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mBtnTextView = findViewById(R.id.btn_textView);
        mBtnTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NormalMenuActivity.this, TextViewActivity.class);
                startActivity(i);
            }
        });
        mBtnRadioCheck = findViewById(R.id.btn_radiocheck);
        mBtnRadioCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NormalMenuActivity.this, RadioCheckActivity.class);
                startActivity(i);
            }
        });
        mBtnProgress = findViewById(R.id.btn_progress);
        mBtnProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NormalMenuActivity.this, ProgressActivity.class);
                startActivity(i);
            }
        });
        mBtnAlertPopup = findViewById(R.id.btn_alertpopup);
        mBtnAlertPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NormalMenuActivity.this, AlertPopupActivity.class);
                startActivity(i);
            }
        });
        findViewById(R.id.btn_viewpager).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NormalMenuActivity.this, ViewPagerActivity.class);
                startActivity(i);
            }
        });
    }
}