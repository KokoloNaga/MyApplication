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

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class SwipeRefreshMoreActivity extends AppCompatActivity {
    private List<String> datas;
    private RecyclerView rv_swipe;
    public static final int UP_TO_REFRESH = 2;
    private RecyclerViewMoreAdapter adapter;
    private boolean isLoadMore = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_swipe_refresh_more);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initData();
        adapter = new RecyclerViewMoreAdapter(SwipeRefreshMoreActivity.this, datas);

        rv_swipe = findViewById(R.id.rv_swipeMore);
        rv_swipe.setLayoutManager(new LinearLayoutManager(SwipeRefreshMoreActivity.this));
        rv_swipe.setAdapter(adapter);
        // 滚动RecyclerView触发的事件
        rv_swipe.addOnScrollListener(onScrollListener);
    }

    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            // 强大的LayoutManager
            RecyclerView.LayoutManager mLayoutManager = rv_swipe.getLayoutManager();
            int lastVisibleItem = ((LinearLayoutManager)mLayoutManager).findLastVisibleItemPosition();  // 由LinearLayoutManager得到当前可见的最后一个item的position
            int totalItemCount = mLayoutManager.getItemCount();  // 由LayoutManager获得item总数

            if(lastVisibleItem >= totalItemCount - 1 && dy >0){  // 界面出现最后一个Item且处于下拉状态
                if(!isLoadMore){
                    loadMore();  // 加载更多
                    isLoadMore = true;
                }
            }
        }
    };

    public void loadMore(){
        handler.sendEmptyMessageDelayed(UP_TO_REFRESH, 1000);
    }

    private void initData(){
        datas = new ArrayList<>();
        for(int i = 0;i < 30;i++){
            datas.add("item:" + i);
        }
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            switch (msg.what){
                case UP_TO_REFRESH:
                    for(int i = 0;i < 3;i++){
                        datas.add("load more item:" + i);
                    }
                    adapter.notifyDataSetChanged();
                    isLoadMore = false;  // 加载更多完成

            }

        }
    };
}