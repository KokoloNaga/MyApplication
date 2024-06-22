package com.example.myapplication.moreApplication;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

public class NotificationActivity extends AppCompatActivity {
    private final int ID = 0;
    private final String channelId = "my_channel_id";  // 不要加0
    private final String name = "ChannelName";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notification);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        findViewById(R.id.btn_startNotification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buildNotification();
            }
        });
        findViewById(R.id.btn_updateNotification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateNotification(99);
            }
        });
        findViewById(R.id.btn_deleteNotification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteNotification();
            }
        });
        findViewById(R.id.btn_my_notification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myNotification();
            }
        });

    }

    public void buildNotification(){

        Notification.Builder builder = new Notification.Builder(NotificationActivity.this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("标题")      // 设置通知标题
                .setContentText("内容")
                .setPriority(Notification.PRIORITY_DEFAULT);  // 设置优先级

        // 点击跳转事件
        Intent intent = new Intent(NotificationActivity.this, EmptyActivity.class);
        PendingIntent ClickPending = PendingIntent.getActivity(NotificationActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        builder.setContentIntent(ClickPending);

        builder.setAutoCancel(true);     // 点击后自动取消

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {  // 8.0系统特有，需要设计Channel
            NotificationChannel mChannel = new NotificationChannel(channelId, name, NotificationManager.IMPORTANCE_LOW);
            manager.createNotificationChannel(mChannel);
            builder.setChannelId(channelId);
        }


        manager.notify(ID, builder.build());   // 向系统发通知，第一个参数为通知id，第二个为Notification对象
    }

    // 更新Notification，只要NotificationId不变，其他与创建一致
    public void updateNotification(int number){
        Notification.Builder builder = new Notification.Builder(NotificationActivity.this);
        builder.setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("标题-更新")      // 设置通知标题
                .setContentText("内容-更新")
                .setPriority(Notification.PRIORITY_DEFAULT) // 设置优先级
                .setNumber(number);

        // 点击跳转事件
        Intent intent = new Intent(NotificationActivity.this, EmptyActivity.class);
        PendingIntent ClickPending = PendingIntent.getActivity(NotificationActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        builder.setContentIntent(ClickPending);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {  // 8.0系统特有，需要设计Channel
            NotificationChannel mChannel = new NotificationChannel(channelId, name, NotificationManager.IMPORTANCE_LOW);
            manager.createNotificationChannel(mChannel);
            builder.setChannelId(channelId);
        }


        manager.notify(ID, builder.build());   // 向系统发通知，第一个参数为通知id，第二个为Notification对象
    }

    public void deleteNotification(){
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancel(ID);
    }

    // 自定义Notification
    public void myNotification(){
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.layout_custom_notification);
        remoteViews.setImageViewResource(R.id.iv_notification, R.mipmap.ic_launcher);
        remoteViews.setTextViewText(R.id.tv_title, "这是标题");
        remoteViews.setTextViewText(R.id.tv_content_notificaiton, "这是内容");

        Intent intent = new Intent(NotificationActivity.this, EmptyActivity.class);
        PendingIntent ClickPending = PendingIntent.getActivity(NotificationActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        Notification.Builder builder = new Notification.Builder(NotificationActivity.this);
        builder.setContent(remoteViews);
        builder.setContentIntent(ClickPending);



        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {  // 8.0系统特有，需要设计Channel
            NotificationChannel mChannel = new NotificationChannel(channelId, name, NotificationManager.IMPORTANCE_LOW);
            manager.createNotificationChannel(mChannel);
            builder.setChannelId(channelId);
        }

        Notification notification = builder.build();
        notification.icon = android.R.drawable.ic_media_play;
        manager.notify(ID, notification);
    }
}