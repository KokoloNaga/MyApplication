package com.example.myapplication.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

public class AnimActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH){
            // 版本号大于4.4
            Transition explode = TransitionInflater.from(AnimActivity.this).inflateTransition(R.transition.explode);
            Transition slide = TransitionInflater.from(AnimActivity.this).inflateTransition(R.transition.slide);
            Transition fade = TransitionInflater.from(AnimActivity.this).inflateTransition(R.transition.fade);
            //getWindow().setEnterTransition(explode);
            //getWindow().setEnterTransition(slide);
            //getWindow().setEnterTransition(fade);
        }


        setContentView(R.layout.activity_anim);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        findViewById(R.id.btn_anim_left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnimActivity.this, ActivityMenuActivity.class);
                startActivity(intent);
                // overridePendingTransaction含两个参数，新Activity进场动画，旧Activitu离场动画
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });
        findViewById(R.id.btn_anim_interpolator).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnimActivity.this, ActivityMenuActivity.class);
                startActivity(intent);
                // overridePendingTransaction含两个参数，新Activity进场动画，旧Activitu离场动画
                overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
            }
        });



        findViewById(R.id.btn_explode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnimActivity.this, ActivityMenuActivity.class);
                startActivity(intent);
                ActivityOptions.makeSceneTransitionAnimation(AnimActivity.this).toBundle();
            }
        });

        findViewById(R.id.btn_slide).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnimActivity.this, ActivityMenuActivity.class);
                startActivity(intent);
                ActivityOptions.makeSceneTransitionAnimation(AnimActivity.this).toBundle();
            }
        });
        findViewById(R.id.btn_fade).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnimActivity.this, ActivityMenuActivity.class);
                startActivity(intent);
                ActivityOptions.makeSceneTransitionAnimation(AnimActivity.this).toBundle();
            }
        });
    }
}