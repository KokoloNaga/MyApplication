package com.example.myapplication.broadcast_receiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.myapplication.R;

public class DynamicBroadcastActivity extends AppCompatActivity {
    public static final String ACTION_DYNAMIC_BROADCAST = "android.intent.action.DYNAMIC_BROADCAST";
    public static final String ACTION_LOCAL_BROADCAST = "android.intent.action.LOCAL_BROADCAST";
    private DynamicBroadcast dynamicBroadcast;
    private LocalBroadcastManager localBroadcastManager;
    private LocalBroadcastReceiver localBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dynamic_broadcast);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dynamicBroadcast = new DynamicBroadcast();
        IntentFilter intentFilter = new IntentFilter(ACTION_DYNAMIC_BROADCAST);
        // 注册广播，包括广播对象与intentFilter
        registerReceiver(dynamicBroadcast, intentFilter);

        findViewById(R.id.btn_dynamic_broadcast_send_message).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ACTION_DYNAMIC_BROADCAST);
                intent.putExtra("data", "Dynamic Broadcast Parameter");
                sendBroadcast(intent);  // 传递信息给广播
            }
        });


        // 应用内广播
        localBroadcastManager = LocalBroadcastManager.getInstance(DynamicBroadcastActivity.this);
        localBroadcastReceiver = new LocalBroadcastReceiver();
        localBroadcastManager.registerReceiver(localBroadcastReceiver, new IntentFilter(ACTION_LOCAL_BROADCAST));

        // 发送信息给LocalBroadcast
        findViewById(R.id.btn_localBroadcastManager).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent localIntent = new Intent(ACTION_LOCAL_BROADCAST);
                localIntent.putExtra("data", "Local Broadcast Receiver");
                localBroadcastManager.sendBroadcast(localIntent);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.i("DynamicBroadcastActivity onDestroy", "销毁广播");
        unregisterReceiver(dynamicBroadcast);
        localBroadcastManager.unregisterReceiver(localBroadcastReceiver);
    }
}