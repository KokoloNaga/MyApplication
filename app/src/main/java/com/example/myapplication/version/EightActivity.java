package com.example.myapplication.version;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;
import com.example.myapplication.moreApplication.EmptyActivity;

public class EightActivity extends AppCompatActivity {
    private CharSequence name = "渠道名称";
    private String description = "渠道描述";
    private String channelId = "channelID1";

    private DynamicBroadcast broadcastReceiver;

    private final String BROADCAST_ACTION = ".version.DynamicBroadcast";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_eight);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        broadcastReceiver = new DynamicBroadcast();
        IntentFilter filter = new IntentFilter(BROADCAST_ACTION);
        registerReceiver(broadcastReceiver, filter);

        findViewById(R.id.btn_notification_channel).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    // 渠道最重要的三部分为名称，id，和重要性级别

                    int importance = NotificationManager.IMPORTANCE_DEFAULT;

                    NotificationChannel channel = new NotificationChannel(channelId, name, importance);
                    channel.setDescription(description);
                    channel.enableLights(true);     // 是否使用通知指示灯
                    channel.enableVibration(true);  // 是否震动

                    NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    manager.createNotificationChannel(channel);

                    Notification.Builder builder = new Notification.Builder(EightActivity.this, channelId);
                    builder.setSmallIcon(R.mipmap.ic_launcher)
                            .setContentTitle("标题")
                            .setContentText("内容")
                            .setNumber(3);  // 通知栏这条通知允许出现三次

                    Intent intent = new Intent(EightActivity.this, EmptyActivity.class);
                    PendingIntent pendingIntent = PendingIntent.getActivity(EightActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
                    builder.setContentIntent(pendingIntent);

                    manager.notify(5, builder.build());

                    findChannelMessage();

                }


            }
        });


        findViewById(R.id.btn_channel_open).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 打卡通知渠道界面
                Intent intent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
                intent.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
                intent.putExtra(Settings.EXTRA_CHANNEL_ID, channelId);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_channel_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    manager.deleteNotificationChannel(channelId);
                }
            }
        });

        // 通过notification发送广播
        findViewById(R.id.btn_channel_broadcast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    // 渠道最重要的三部分为名称，id，和重要性级别

                    int importance = NotificationManager.IMPORTANCE_DEFAULT;

                    NotificationChannel channel = new NotificationChannel(channelId, name, importance);
                    channel.setDescription(description);

                    NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    manager.createNotificationChannel(channel);

                    Notification.Builder builder = new Notification.Builder(EightActivity.this, channelId);
                    builder.setSmallIcon(R.mipmap.ic_launcher)
                            .setContentTitle("标题1")
                            .setContentText("内容1")
                            .setNumber(3);  // 通知栏这条通知允许出现三次

                    Intent intent = new Intent(BROADCAST_ACTION);
                    intent.putExtra("data", "12345");
                    // 设置广播
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(EightActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
                    builder.setContentIntent(pendingIntent);
                    builder.setAutoCancel(true);

                    manager.notify(6, builder.build());


                }
            }
        });

    }

    public void findChannelMessage(){
        NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = manager.getNotificationChannel(channelId);
            long[] vibrationPattern = channel.getVibrationPattern();
            if(vibrationPattern != null){
                Log.i("notification", "振动模式：" + vibrationPattern.length);
            }

            Uri uri = channel.getSound();
            Log.i("notification", "通知声音：" + uri.toString());

            int importance = channel.getImportance();
            Log.i("notification", "通知等级：" + importance);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }


}