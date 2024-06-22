package com.example.myapplication.activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

public class ToastActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_toast);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        findViewById(R.id.btn_toastcustom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ToastActivity.this, "提示", Toast.LENGTH_LONG).show();
            }
        });
        findViewById(R.id.btn_toasttop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustiomToast.getInstance().showToastCustom(ToastActivity.this, "显示顶部", Gravity.TOP);
            }
        });
        findViewById(R.id.btn_toastmiddle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustiomToast.getInstance().showToastCustom(ToastActivity.this, "显示中部", Gravity.CENTER);
            }
        });
        findViewById(R.id.btn_toastbottom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustiomToast.getInstance().showToastCustom(ToastActivity.this, "显示底部", Gravity.BOTTOM);
            }
        });
    }
}