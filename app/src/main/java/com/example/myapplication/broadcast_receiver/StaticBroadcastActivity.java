package com.example.myapplication.broadcast_receiver;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;
/*
静态广播，在AndroidManifest中声明
 */
public class StaticBroadcastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_static_broadcast);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        findViewById(R.id.btn_static_broadcast_send_message).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 直接传递，不需要声明注册，Intent中标签需要与AndroidManifest中一致
                Intent staticIntent = new Intent();
                staticIntent.setAction("android.intent.action.STATIC_BROADCAST");
                staticIntent.putExtra("data", "Static Broadcast Parameter");
                // Android 8.0 独有
                staticIntent.setComponent(new ComponentName(StaticBroadcastActivity.this, StaticBroadcast.class));
                sendBroadcast(staticIntent);
            }
        });
    }
}