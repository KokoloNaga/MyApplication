package com.example.myapplication.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;

public class FragmentContainer extends Fragment {
    private int fragmentIndex;

    public FragmentContainer(){

    }

    @SuppressLint("ValidFragment")
    public FragmentContainer(int fragmentIndex){
        this.fragmentIndex = fragmentIndex;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_container, null);
        TextView tvContent = view.findViewById(R.id.tv_threefragment);
        if(fragmentIndex == 1){
            tvContent.setText("第一个Fragment");
            tvContent.setBackgroundResource(android.R.color.holo_red_dark);
        } else if(fragmentIndex == 2){
            tvContent.setText("第二个Fragment");
            tvContent.setBackgroundResource(android.R.color.holo_orange_dark);
        } else if(fragmentIndex == 3){
            tvContent.setText("第三个Fragment");
            tvContent.setBackgroundResource(android.R.color.holo_blue_dark);
        }
        return view;
    }
}
