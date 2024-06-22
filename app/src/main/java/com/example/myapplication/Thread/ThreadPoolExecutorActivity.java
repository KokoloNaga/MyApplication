package com.example.myapplication.Thread;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolExecutorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_thread_pool_executor);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        threadPoolExecutor = new ThreadPoolExecutor(
                2,  // 核心线程
                5,  // 最大线程数
                30, // 线程保持时间
                TimeUnit.SECONDS,   // 时间单位
                new LinkedBlockingQueue<Runnable>(128),     // 阻塞队列，这里是无界队列，长度128
                sThreadFactory,     // 线程工厂
                handler     // 拒绝策略
        );

        for(int i = 0;i < 10;i++){
            final int ivalue = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    SystemClock.sleep(1000);
                    Log.i("ansen", "当前线程id：" + android.os.Process.myTid() + " ivalue：" + ivalue);
                }
            };
            threadPoolExecutor.execute(runnable);
        }
    }

    private MyRejectExecutionHandler handler = new MyRejectExecutionHandler();
    private ThreadPoolExecutor threadPoolExecutor;

    private static final ThreadFactory sThreadFactory = new ThreadFactory() {
        // 可以在并发情况下达到原子更新，避免使用synchronized（加锁），而且性能非常高
        private final AtomicInteger mCount = new AtomicInteger(1);
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "ThreadPoolExecutor new Thread #" + mCount.getAndIncrement());
        }
    };



}