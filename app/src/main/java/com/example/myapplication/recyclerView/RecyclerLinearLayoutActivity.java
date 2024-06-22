package com.example.myapplication.recyclerView;

import android.os.Bundle;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerLinearLayoutActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<String> datas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recycler_linear_layout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initData();

        recyclerView = findViewById(R.id.recyclerview);

        // 针对recyclerView 设置LayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(RecyclerLinearLayoutActivity.this));
        // 设置Adapter
        recyclerView.setAdapter(new RecyclerViewAdapter(RecyclerLinearLayoutActivity.this, datas));
        // 设置装饰
        recyclerView.addItemDecoration(new DividerItemDecoration(RecyclerLinearLayoutActivity.this));

    }

    private void initData(){
        datas = new ArrayList<>();
        for(int i = 0;i < 100;i++){
            datas.add("item:" + i);
        }
    }
}