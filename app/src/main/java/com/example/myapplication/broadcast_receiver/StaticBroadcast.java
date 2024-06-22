package com.example.myapplication.broadcast_receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/*
静态注册广播，AndroidManifest注册，不能执行长时操作，顶多加了Thread
 */
public class StaticBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String data = intent.getStringExtra("data");
        Log.i("data", data);
    }
}