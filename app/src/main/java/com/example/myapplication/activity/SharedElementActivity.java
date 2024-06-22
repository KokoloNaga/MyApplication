package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

public class SharedElementActivity extends AppCompatActivity{
    private View viewSharedOne;
    private TextView viewSharedTwo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_shared_element);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.ll_root), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        viewSharedOne = findViewById(R.id.view_share_one);
        viewSharedTwo = findViewById(R.id.view_share_two);

        findViewById(R.id.ll_root).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        viewSharedTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SharedElementActivity.this, SecondSharedElemActivity.class);

                // 构造Pair对象，绑定对象及对应的名称
                // 构造ActivityOptionsCompat对象，由当前Activity和所有Pairs构成
                ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(SharedElementActivity.this,
                                new androidx.core.util.Pair<>(viewSharedOne, ViewCompat.getTransitionName(viewSharedOne)),
                                new androidx.core.util.Pair<>(viewSharedTwo, ViewCompat.getTransitionName(viewSharedTwo)));
                ActivityCompat.startActivity(SharedElementActivity.this, intent, transitionActivityOptions.toBundle());
            }
        });
    }
}