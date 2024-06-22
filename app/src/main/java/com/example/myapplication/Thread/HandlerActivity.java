package com.example.myapplication.Thread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

public class HandlerActivity extends AppCompatActivity {
    private TextView textView;
    public final static int UPDATE_UI = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_handler);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 通过handler回调
        handler.post(runnable); // 执行
        handler.postDelayed(runnable, 2000);  // 延迟两秒执行

        textView = findViewById(R.id.tv_handlerUpdate);

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 1;i < 100;i++){
                    Log.i("Activity", "当前值是" + i);
                    Message message = handler.obtainMessage();
                    message.what = UPDATE_UI;
                    message.obj = i;
                    handler.sendMessage(message);
                    try {
                        Thread.sleep(200);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            switch (msg.what){
                case UPDATE_UI:
                    textView.setText("当前值是" + msg.obj);
            }
        }
    };
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Log.i("Activity", "Handler Runnable");
        }
    };
}