package com.example.myapplication.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

public class ServiceActivity extends AppCompatActivity{
    private LocalService localService;
    private boolean mIsBound;  // 是否绑定

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_service);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Log.i("ServiceActivity", "onCreate Thread ID:" + android.os.Process.myTid());

        findViewById(R.id.btn_startService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(ServiceActivity.this, LocalService.class));
            }
        });
        findViewById(R.id.btn_stopService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(ServiceActivity.this, LocalService.class));
            }
        });
        findViewById(R.id.btn_bind_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceActivity.this, LocalService.class);
                // 绑定service，含intent，serviceConnection，和flag，flag这里是bind后，若service未启动，service自动启动
                bindService(intent, serviceConnection, BIND_AUTO_CREATE);
                mIsBound = true;
            }
        });
        findViewById(R.id.btn_unbind_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mIsBound){
                    unbindService(serviceConnection);
                    mIsBound = false;
                }
            }
        });

    }

    ServiceConnection serviceConnection = new ServiceConnection() {  // 绑定Activity和Service
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {  // 建立连接后触发的事件
            localService = ((LocalService.LocalBinder) service).getService();
            localService.downMusic();  // 调用Service中的后台方法
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {  // 接触连接后触发的事件
            localService = null;
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
    }
}