package com.example.myapplication.recyclerView;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerGridLayoutActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<String> datas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recycler_grid_layout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initData();

        // 结构与LinearRecyclerView基本一致，LinearLayoutManager换成GridLayoutManager
        recyclerView = findViewById(R.id.rv_grid);
        recyclerView.setLayoutManager(new GridLayoutManager(RecyclerGridLayoutActivity.this,2));  // 每行2个Item
        recyclerView.setAdapter(new RecyclerViewAdapter(RecyclerGridLayoutActivity.this, datas));

        // 设置分隔线的装饰
        recyclerView.addItemDecoration(new DividerGridItemDecoration(RecyclerGridLayoutActivity.this));


    }

    private void initData(){
        datas = new ArrayList<>();
        for(int i = 0;i < 100;i++){
            datas.add("item:" + i);
        }
    }
}