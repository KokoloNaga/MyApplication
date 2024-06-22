package com.example.myapplication.fragment;

import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

public class FragmentOneActivity extends AppCompatActivity implements View.OnClickListener{
    private FragmentOne staticFragment;
    private FragmentContainer fragmentOne;
    private FragmentContainer fragmentTwo;
    private FragmentContainer fragmentThree;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fragment_one);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        staticFragment = (FragmentOne) getSupportFragmentManager().findFragmentById(R.id.fragment_one);
        staticFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Fragment", "Fragment被点击");
            }
        });


        fragmentOne = new FragmentContainer(1);
        fragmentTwo = new FragmentContainer(2);
        fragmentThree = new FragmentContainer(3);


        findViewById(R.id.btn_add_fragment).setOnClickListener(this);
        findViewById(R.id.btn_remove_fragment).setOnClickListener(this);
        findViewById(R.id.btn_replace_fragment).setOnClickListener(this);
        findViewById(R.id.btn_hide_fragment).setOnClickListener(this);
        findViewById(R.id.btn_show_fragment).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        // 通过FragmentManager开启Fragment事务
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(v.getId() == R.id.btn_add_fragment){
            transaction.add(R.id.fl_container, fragmentOne);
            transaction.add(R.id.fl_container, fragmentTwo);
        } else if(v.getId() == R.id.btn_remove_fragment){
            transaction.remove(fragmentTwo);
        } else if(v.getId() == R.id.btn_replace_fragment){
            transaction.replace(R.id.fl_container, fragmentThree);
        } else if(v.getId() == R.id.btn_hide_fragment){
            transaction.hide(fragmentThree);
        } else if(v.getId() == R.id.btn_show_fragment){
            transaction.show(fragmentThree);
        }
        transaction.commit();
    }
}