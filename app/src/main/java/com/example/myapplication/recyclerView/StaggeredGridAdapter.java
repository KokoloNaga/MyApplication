package com.example.myapplication.recyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.List;

public class StaggeredGridAdapter extends RecyclerView.Adapter<StaggeredGridAdapter.MyViewHolder>{
    private List<ItemData> datas;
    private LayoutInflater inflater;
    public StaggeredGridAdapter(Context context, List<ItemData> datas){
        inflater = LayoutInflater.from(context);
        this.datas = datas;
    }

    @NonNull
    @Override
    public StaggeredGridAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.recycler_staggered_item, null);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StaggeredGridAdapter.MyViewHolder holder, int position) {
        ItemData itemData = datas.get(position);
        holder.textView.setText(itemData.getContent());
        holder.textView.setHeight(itemData.getHeight());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.tv_staggered);
        }
    }
}
