package com.example.myapplication.recyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

public class ComplexViewMenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_complex_view_menu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        findViewById(R.id.btn_recyclerview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ComplexViewMenuActivity.this, RecyclerLinearLayoutActivity.class);
                startActivity(i);
            }
        });
        findViewById(R.id.btn_recycler_grid_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ComplexViewMenuActivity.this, RecyclerGridLayoutActivity.class);
                startActivity(i);
            }
        });

        findViewById(R.id.btn_recycler_staggered_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ComplexViewMenuActivity.this, RecyclerStaggeredActivity.class);
                startActivity(i);
            }
        });
        findViewById(R.id.btn_recycler_headfootclick_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ComplexViewMenuActivity.this, RecyclerViewHeadFootClickActivity.class);
                startActivity(i);
            }
        });
        findViewById(R.id.btn_recycler_swipe_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ComplexViewMenuActivity.this, SwipeRefreshActivity.class);
                startActivity(i);
            }
        });
        findViewById(R.id.btn_recycler_swipeMore_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ComplexViewMenuActivity.this, SwipeRefreshMoreActivity.class);
                startActivity(i);
            }
        });
    }
}