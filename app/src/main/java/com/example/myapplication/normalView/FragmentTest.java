package com.example.myapplication.normalView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;

@SuppressLint("ValidFragment")
public class FragmentTest extends Fragment {
    private String content;
    private int backgroundResourceId;

    public FragmentTest(String content, int backgroundResourceId) {
        this.content = content;
        this.backgroundResourceId = backgroundResourceId;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_test, null);
        TextView tvContent = rootView.findViewById(R.id.tv_content);
        tvContent.setText(content);
        rootView.setBackgroundResource(backgroundResourceId);
        // Inflate the layout for this fragment
        return rootView;
    }
}