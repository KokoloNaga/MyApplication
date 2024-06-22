package com.example.myapplication.recyclerView;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    // List datas代表每个Item的内容
    private List<String> datas;
    private LayoutInflater inflater;

    public RecyclerViewAdapter(Context context, List<String> datas){
        inflater = LayoutInflater.from(context);
        this.datas = datas;
    }

    // 针对每个Item的View用ViewHolder包装
    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_item, null);
        return new MyViewHolder(view);
    }
    // 针对每个ItemView的逻辑操作
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.textView.setText(datas.get(position));
    }

    // 返回recyclerview的总数，与datas总数一致
    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.tv_adapter);
        }
    }
}
