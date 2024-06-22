package com.example.myapplication.recyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class SwipeRefreshActivity extends AppCompatActivity {

    public static final int PULL_TO_REFRESH = 1;  // 刷新
    private List<String> datas;
    private RecyclerView rv_swipe;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_swipe_refresh);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.swipeRefreshLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initData();
        adapter = new RecyclerViewAdapter(SwipeRefreshActivity.this, datas);

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 下拉刷新执行的内容
                handler.sendEmptyMessageDelayed(PULL_TO_REFRESH, 3000);
            }
        });
        swipeRefreshLayout.setColorSchemeResources(R.color.purple_200,
                android.R.color.holo_green_light,
                com.google.android.material.R.color.design_default_color_primary);  // 设置下拉箭头颜色（设计多个）

        rv_swipe = findViewById(R.id.rv_swipe);
        rv_swipe.setLayoutManager(new LinearLayoutManager(SwipeRefreshActivity.this));
        rv_swipe.addItemDecoration(new DividerItemDecoration(SwipeRefreshActivity.this));
        rv_swipe.setAdapter(adapter);


    }

    private void initData(){
        datas = new ArrayList<>();
        for(int i = 0;i < 15;i++){
            datas.add("item:" + i);
        }
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            switch (msg.what){
                case PULL_TO_REFRESH:
                    if(datas.size() > 1){
                        datas.remove(0);
                        adapter.notifyDataSetChanged();  // 更新adapter中数据集
                        swipeRefreshLayout.setRefreshing(false);  // false指刷新完成， true代表刷新正在进行
                    }
                    break;
            }
        }
    };
}