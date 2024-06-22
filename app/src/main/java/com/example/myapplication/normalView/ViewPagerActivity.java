package com.example.myapplication.normalView;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.R;

public class ViewPagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_pager);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ViewPager viewPager = findViewById(R.id.pg1);
        viewPager.setOffscreenPageLimit(2);  // 设置缓存页数为2
        viewPager.setCurrentItem(0);  // 设置当前展示的页为0号页面
        // 与RecyclerView类似，ViewPager也需要adapter来设置ViewPager
        FragmentAdapter pagerAdapter = new FragmentAdapter(getSupportFragmentManager());

        pagerAdapter.addFragment(new FragmentTest("页面1", android.R.color.holo_red_dark));
        pagerAdapter.addFragment(new FragmentTest("页面2", android.R.color.holo_green_dark));
        pagerAdapter.addFragment(new FragmentTest("页面3", android.R.color.holo_red_dark));
        pagerAdapter.addFragment(new FragmentTest("页面4", android.R.color.holo_green_dark));

        viewPager.setAdapter(pagerAdapter);

        // 设置页面改变监听事件
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // 页面只要一波动就会发生
                Log.i("ansen", "页面滑动");
            }

            @Override
            public void onPageSelected(int position) {
                // 只有成功一次翻页才发生，一定要翻页
                Log.i("ansen", "页面选中");
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // 开始滑动和停止滑动发生，无论是否翻页
                Log.i("ansen", "滑动状态开始改变");
            }
        });
    }
}