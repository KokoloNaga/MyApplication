package com.example.myapplication.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class LocalService extends Service {
    private final IBinder mBinder = new LocalBinder();
    public LocalService() {
    }

    // create
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("LocalService", "onCreate");
        Log.i("LocalService", "onCreate Thread ID:" + android.os.Process.myTid());
    }

    // start
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "onStartCommand start id " + startId);
        return super.onStartCommand(intent, flags, startId);

    }

    // destroy
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("LocalService", "onDestroy");
    }

    // 实现与Activity之间的通信
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("LocalService", "onBind");
        return mBinder;
    }

    public void downMusic(){
        Log.i("LocalService", "downMusic");
    }

    public class LocalBinder extends Binder{
        // 返回当前的Service本体
        LocalService getService(){
            return LocalService.this;
        }

    }

}