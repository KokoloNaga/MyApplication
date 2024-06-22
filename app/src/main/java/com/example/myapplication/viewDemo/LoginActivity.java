package com.example.myapplication.viewDemo;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

public class LoginActivity extends AppCompatActivity {
    private LinearLayout llRootView;
    private EditText etPassword;
    private ImageView ivOne, ivSelect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ivOne = findViewById(R.id.iv_one);
        ivOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivOne.setImageResource(R.drawable.ic_launcher_foreground);
            }
        });

        ivSelect = findViewById(R.id.iv_select);
        ivSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 来回不停取反
                ivSelect.setSelected(!ivSelect.isSelected());
            }
        });

        llRootView = findViewById(R.id.ll_root_view);
        findViewById(R.id.btn_add_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = new TextView(LoginActivity.this);
                textView.setText("动态添加View");
                llRootView.addView(textView);
            }
        });

        // EditText监听改变内容，只要改变一下（添加或删除）就会触发
        etPassword = findViewById(R.id.et_password);
        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // s为改变前
                Log.i("Ansen", "内容改变前调用：" + s);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // s为改变后
                Log.i("Ansen","内容发生改变，可以告诉服务器：" + s);
            }

            @Override
            public void afterTextChanged(Editable s) {
                // s为改变后
                Log.i("Ansen", "内容改变之后调用：" + s);
            }
        });
    }
}