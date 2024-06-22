package com.example.myapplication.recyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewHeadFootClickActivity extends AppCompatActivity {
    private List<String> datas;
    private RecyclerView rvHeadFoot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recycler_view_head_foot_click);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initData();

        rvHeadFoot = findViewById(R.id.rv_headfoot);
        rvHeadFoot.setLayoutManager(new LinearLayoutManager(RecyclerViewHeadFootClickActivity.this));
        rvHeadFoot.addItemDecoration(new DividerItemDecoration(RecyclerViewHeadFootClickActivity.this));

        RecyclerHeadFootViewAdapter adapter = new RecyclerHeadFootViewAdapter(RecyclerViewHeadFootClickActivity.this, datas);

        View Header = LayoutInflater.from(RecyclerViewHeadFootClickActivity.this).inflate(R.layout.recycler_header, rvHeadFoot, false);  // 布尔值代表返回的View是否含父View
        adapter.setHeader(Header);

        View footer = LayoutInflater.from(RecyclerViewHeadFootClickActivity.this).inflate(R.layout.recycler_footer, rvHeadFoot, false);
        adapter.setFooter(footer);

        rvHeadFoot.setItemAnimator(new DefaultItemAnimator());  // 增加删除动画

        RecyclerHeadFootViewAdapter.RecyclerViewItemClick recyclerViewItemClick = new RecyclerHeadFootViewAdapter.RecyclerViewItemClick() {
            @Override
            public void onItemClick(int realPosition, int position) {
                datas.remove(realPosition);

                Log.i("ansen", "删除数据：" + realPosition + " View位置：" + position);
                Log.i("ansen", "当前位置" + position + " 更新item数量：" + datas.size());

                adapter.notifyItemRemoved(position);  // 移除item
                adapter.notifyItemRangeChanged(position, adapter.getItemCount() - position - 1);  // adapter范围更新
            }
        };
        // 设置点击事件触发的内容
        adapter.setRecyclerViewItemClick(recyclerViewItemClick);
        rvHeadFoot.setAdapter(adapter);
        rvHeadFoot.addItemDecoration(new DividerItemDecoration(RecyclerViewHeadFootClickActivity.this));



    }

    private void initData(){
        datas = new ArrayList<>();
        for(int i = 0;i < 5;i++){
            datas.add("item:" + i);
        }
    }
}