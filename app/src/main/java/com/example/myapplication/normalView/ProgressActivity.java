package com.example.myapplication.normalView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

public class ProgressActivity extends AppCompatActivity {
    private Button btnAdd, btnReduce, btnProgresDialog, btnLoad;
    private ProgressBar progressBar,pbRun;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_progress);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        progressBar = findViewById(R.id.pb_horizontal);
        btnAdd = findViewById(R.id.btn_add);
        btnReduce = findViewById(R.id.btn_reduce);
        pbRun = findViewById(R.id.pb_run);
        btnLoad = findViewById(R.id.btn_load);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setProgress(progressBar.getProgress() + 10);
            }
        });
        btnReduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setProgress(progressBar.getProgress() - 10);
            }
        });
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(runnable).start();
            }
        });



        btnProgresDialog = findViewById(R.id.btn_showPD);
        btnProgresDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(ProgressActivity.this);
                progressDialog.setTitle("这是标题");
                progressDialog.setMessage("这是内容");
                progressDialog.incrementProgressBy(20);  // 设置进度值
                progressDialog.setCancelable(false);
                progressDialog.show();
                // 开发线程执行耗时操作
                new Thread(){
                    @Override
                    public void run() {
                        super.run();

                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e){
                            e.printStackTrace();
                        }
                        Message m = new Message();
                        m.what = 1;
                        handler.sendMessage(m);
                    }
                }.start();
            }
        });


    }
    // 实现线程的Run接口，由于Handler中要重复调用，因此单独拎出来
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            pbRun.setProgress(pbRun.getProgress() + 5);
            Message m = new Message();
            m.what = 2;
            handler.sendMessage(m);
        }
    };

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    progressDialog.dismiss();
                    break;
                case 2:
                    if(pbRun.getProgress() < 100){
                        handler.postDelayed(runnable, 500);
                    }else {
                        Toast.makeText(ProgressActivity.this, "加载完成", Toast.LENGTH_LONG).show();
                    }
            }

        }
    };

}