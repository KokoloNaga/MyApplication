package com.example.myapplication.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.myapplication.R;

public class FragmentOne extends Fragment {
    private View rootView;
    private View.OnClickListener onClickListener;

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.i("FragmentOne", "onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("FragmentOne", "onCreate");
    }

    @Nullable
    @Override
    // fragment位置放入View
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("FragmentOne", "onCreateView");
        rootView = inflater.inflate(R.layout.fragment_one, null);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("FragmentOne", "onActivityCreated");

        if(onClickListener != null){
            rootView.setOnClickListener(onClickListener);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("FragmentOne", "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("FragmentOne", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("FragmentOne", "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("FragmentOne", "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("FragmentOne", "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("FragmentOne", "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("FragmentOne", "onDetach");
    }
}
