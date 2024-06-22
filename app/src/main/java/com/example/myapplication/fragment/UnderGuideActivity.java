package com.example.myapplication.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.R;

public class UnderGuideActivity extends AppCompatActivity {
    private HomeFragment homeFragment;
    private DynamicFragment dynamicFragment;
    private MessageFragment messageFragment;
    private PersonFragment personFragment;
    private int currentId = R.id.tv_main;

    private TextView tvMain, tvDymanic, tvMessage, tvPerson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_under_guide);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvMain = findViewById(R.id.tv_main);
        tvMain.setSelected(true);
        tvDymanic = findViewById(R.id.tv_dynamic);
        tvMessage = findViewById(R.id.tv_message);
        tvPerson = findViewById(R.id.tv_person);

        homeFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fl_main_container, homeFragment).commit();     // 初始状态FrameLayout显示homeFragment

        tvMain.setOnClickListener(tabClickListener);
        tvDymanic.setOnClickListener(tabClickListener);
        tvMessage.setOnClickListener(tabClickListener);
        tvPerson.setOnClickListener(tabClickListener);
        findViewById(R.id.iv_make).setOnClickListener(onClickListener);

    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.iv_make) {
                Toast.makeText(UnderGuideActivity.this, "点击制作按钮", Toast.LENGTH_LONG).show();
            }
        }
    };

    private View.OnClickListener tabClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId() != currentId){
                changeSelect(v.getId());
                changeFragment(v.getId());
                currentId = v.getId();
            }
        }
    };

    private void changeSelect(int resId){
        tvMain.setSelected(false);
        tvDymanic.setSelected(false);
        tvMessage.setSelected(false);
        tvPerson.setSelected(false);

        if (resId == R.id.tv_main) {
            tvMain.setSelected(true);
        } else if (resId == R.id.tv_dynamic) {
            tvDymanic.setSelected(true);
        } else if (resId == R.id.tv_message) {
            tvMessage.setSelected(true);
        } else if (resId == R.id.tv_person) {
            tvPerson.setSelected(true);
        }
    }
    private void changeFragment(int resId){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        hideFragment(transaction);

        if(resId == R.id.tv_main){
            if(homeFragment == null){
                homeFragment = new HomeFragment();
                transaction.add(R.id.fl_main_container, homeFragment);
            } else {
                transaction.show(homeFragment);
            }
        } else if(resId == R.id.tv_dynamic){
            if(dynamicFragment == null){
                dynamicFragment = new DynamicFragment();
                transaction.add(R.id.fl_main_container, dynamicFragment);
            } else {
                transaction.show(dynamicFragment);
            }
        } else if(resId == R.id.tv_message) {
            if (messageFragment == null) {
                messageFragment = new MessageFragment();
                transaction.add(R.id.fl_main_container, messageFragment);
            } else {
                transaction.show(messageFragment);
            }
        } else if(resId == R.id.tv_person) {
            if (personFragment == null) {
                personFragment = new PersonFragment();
                transaction.add(R.id.fl_main_container, personFragment);
            } else {
                transaction.show(personFragment);
            }
        }

        transaction.commit();

    }

    private void hideFragment(FragmentTransaction transaction){
        if(homeFragment != null){
            transaction.hide(homeFragment);
        }
        if(dynamicFragment != null){
            transaction.hide(dynamicFragment);
        }
        if(messageFragment != null){
            transaction.hide(messageFragment);
        }
        if(personFragment != null){
            transaction.hide(personFragment);
        }

    }
}