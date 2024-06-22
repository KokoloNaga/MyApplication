package com.example.myapplication.normalView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.myapplication.R;

/*
Dialogment为基于Fragment的Dialog，能够实现自定义Dialog，本质上是Fragment，能更好管理
 */
public class MyDialogFragment extends DialogFragment {
    static MyDialogFragment newInstance(){
        return new MyDialogFragment();
    }

    // 自定义dialog的界面设计
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.hello_world, container, false);
        TextView tv = v.findViewById(R.id.textview);
        tv.setText("This is an instance of MyDialogFragment");
        return v;
    }


}
