package com.example.myapplication.broadcast_receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/*
自定义动态注册广播Broadcast
 */
public class DynamicBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {  // 通过intent接收广播
        String data = intent.getStringExtra("data");
        Log.i("data", data);
    }
}