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

public class RecyclerViewMoreAdapter extends RecyclerView.Adapter<RecyclerViewMoreAdapter.MyViewHolder> {
    // List datas代表每个Item的内容
    private List<String> datas;
    private LayoutInflater inflater;

    public RecyclerViewMoreAdapter(Context context, List<String> datas){
        inflater = LayoutInflater.from(context);
        this.datas = datas;
    }

    // 针对每个Item的View用ViewHolder包装
    @NonNull
    @Override
    public RecyclerViewMoreAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_item_processbar, parent, false);
        return new MyViewHolder(view);
    }
    // 针对每个ItemView的逻辑操作
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewMoreAdapter.MyViewHolder holder, int position) {
        holder.textView.setText(datas.get(position));
        if(position > 5 && position == datas.size() - 1){
            holder.progressBar.setVisibility(View.VISIBLE);  // 最后一行item显示进度条
        }else {
            holder.progressBar.setVisibility(View.GONE);  // 非最后一行不显示进度条
        }
    }

    // 返回recyclerview的总数，与datas总数一致
    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        private ProgressBar progressBar;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.pb_more);
            textView = itemView.findViewById(R.id.tv_adapter_more);
        }
    }
}
