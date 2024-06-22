package com.example.myapplication.version;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.moreApplication.EmptyActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;


public class ViewPagerFragment extends Fragment {
    private String name;
    private final int ID = 20;
    private final String channelId = "my_channel_12";  // 不要用0
    private final String name_channel = "ChannelName";
    public ViewPagerFragment(String name){
        this.name = name;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewpager, null);
        TextView textView = view.findViewById(R.id.tv_fragment_viewpager);
        textView.setText(name);

        view.findViewById(R.id.btn_fullscreenNotification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManager manager = (NotificationManager) container.getContext().getSystemService(Context.NOTIFICATION_SERVICE);

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {  // 8.0系统特有，需要设计Channel
                    NotificationChannel mChannel = new NotificationChannel(
                            channelId,
                            name_channel,
                            NotificationManager.IMPORTANCE_HIGH);

                    manager.createNotificationChannel(mChannel);

                }

                Intent intent = new Intent(container.getContext(), EmptyActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(container.getContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(container.getContext(), channelId)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("悬挂式通知标题")
                        .setContentText("悬挂式通知内容")
                        .setPriority(Notification.PRIORITY_HIGH)
                        .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setAutoCancel(true)
                        .setContentIntent(pendingIntent);

                manager.notify(ID, builder.build());

            }
        });



        return view;
    }
}
