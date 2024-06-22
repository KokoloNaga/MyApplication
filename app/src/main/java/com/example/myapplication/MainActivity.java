package com.example.myapplication;

import android.content.ContentProvider;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.Thread.ThreadMenuActivity;
import com.example.myapplication.activity.ActivityMenuActivity;
import com.example.myapplication.broadcast_receiver.BroadcastReceiverMenuActivity;
import com.example.myapplication.contentProvider.ContentProviderActivity;
import com.example.myapplication.fragment.FragmentMenuActivity;
import com.example.myapplication.moreApplication.MoreApplicationActivity;
import com.example.myapplication.recyclerView.ComplexViewMenuActivity;
import com.example.myapplication.normalView.NormalMenuActivity;
import com.example.myapplication.service.ServiceMenuActivity;
import com.example.myapplication.version.FiveActivity;
import com.example.myapplication.version.VersionMenuActivity;
import com.example.myapplication.viewDemo.ViewMenuActivity;
import com.example.myapplication.web_data.WebDataMenuActivity;

public class MainActivity extends AppCompatActivity {
    public Button mBtnViewMenu, mBtnNormalView, mBtnComplexView, mbtnActivity, mBtnService, mBtnBroadcast, mBtnContentProvider, mBtnFragment, mBtnThread, mBtnWebData, mBtnMoreApp, mBtnVersion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mBtnViewMenu = findViewById(R.id.viewMenu);
        mBtnViewMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ViewMenuActivity.class);
                startActivity(i);
            }
        });
        mBtnNormalView = findViewById(R.id.normalView);
        mBtnNormalView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, NormalMenuActivity.class);
                startActivity(i);
            }
        });
        mBtnComplexView = findViewById(R.id.complexView);
        mBtnComplexView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ComplexViewMenuActivity.class);
                startActivity(i);
            }
        });
        mbtnActivity = findViewById(R.id.activity);
        mbtnActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ActivityMenuActivity.class);
                startActivity(i);
            }
        });
        mBtnService = findViewById(R.id.service);
        mBtnService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ServiceMenuActivity.class);
                startActivity(i);
            }
        });
        mBtnBroadcast = findViewById(R.id.broadcast);
        mBtnBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, BroadcastReceiverMenuActivity.class);
                startActivity(i);
            }
        });
        mBtnContentProvider = findViewById(R.id.contentProvider);
        mBtnContentProvider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ContentProviderActivity.class);
                startActivity(i);
            }
        });
        mBtnFragment = findViewById(R.id.fragment);
        mBtnFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, FragmentMenuActivity.class);
                startActivity(i);
            }
        });
        mBtnThread = findViewById(R.id.thread);
        mBtnThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ThreadMenuActivity.class);
                startActivity(i);
            }
        });
        mBtnWebData = findViewById(R.id.webdata);
        mBtnWebData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, WebDataMenuActivity.class);
                startActivity(i);
            }
        });
        mBtnMoreApp = findViewById(R.id.moreApplication);
        mBtnMoreApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MoreApplicationActivity.class);
                startActivity(i);
            }
        });
        mBtnVersion = findViewById(R.id.version);
        mBtnVersion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, VersionMenuActivity.class);
                startActivity(i);
            }
        });
    }
}