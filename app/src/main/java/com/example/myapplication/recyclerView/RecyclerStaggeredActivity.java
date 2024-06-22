package com.example.myapplication.recyclerView;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RecyclerStaggeredActivity extends AppCompatActivity {
    private List<ItemData> datas;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recycler_staggered);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initData();

        // 设置recyclerStaggeredView，分隔线可以用调整itemview的padding，水平排列
        recyclerView = findViewById(R.id.rv_staggered);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(new StaggeredGridAdapter(RecyclerStaggeredActivity.this, datas));
    }

    private void initData(){
        datas = new ArrayList<>();
        for(int i = 0;i < 100;i++){
            datas.add(new ItemData(String.valueOf(i), new Random().nextInt(60) + 62));
        }
    }
}