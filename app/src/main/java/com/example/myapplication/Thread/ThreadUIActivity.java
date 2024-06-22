package com.example.myapplication.Thread;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

public class ThreadUIActivity extends AppCompatActivity {
    private TextView tvContent, tvContentPost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_thread_uiactivity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvContent = findViewById(R.id.tv_ui);
        Log.i("Activity", "主线程id：" + android.os.Process.myTid());
        new Thread(new Runnable() {
            @Override
            public void run() {

                Log.i("Activity", "子线程id：" + android.os.Process.myTid());
                // 通过runOnUiThread更新ui，底层依旧是通过handler实现
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // 本质上发生在主线程中
                        Log.i("Activity", "主线程id：" + android.os.Process.myTid());
                        tvContent.setText("RunOnUiThread更新UI");
                    }
                });
            }
        }).start();

        tvContentPost = findViewById(R.id.tv_ui_post);
        new Thread(new Runnable() {
            @Override
            public void run() {
                tvContentPost.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("Post", "主线程id：" + android.os.Process.myTid());
                        tvContentPost.setText("View Post方式");
                    }
                });
            }
        }).start();
    }
}