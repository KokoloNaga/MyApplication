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


public class RecyclerHeadFootViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<String> datas;
    private LayoutInflater inflater;

    public static final int TYPE_HEADER = 1;  // header类型
    public static final int TYPE_FOOTER = 2;  // footer类型

    private View header = null;  // header对应的view
    private View footer = null;  // footer对应的view
    public RecyclerHeadFootViewAdapter(Context context, List<String> datas) {
        inflater = LayoutInflater.from(context);
        this.datas = datas;
    }
    @NonNull
    @Override
    // 针对不同位置的item设置不同的ViewHolder:针对当前位置，先确定当前位置View的Type，再设置对应的ViewHolder
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == TYPE_HEADER){
            return new RecyclerView.ViewHolder(header){};
        }else if(viewType == TYPE_FOOTER){
            return new RecyclerView.ViewHolder(footer){};
        }

        View itemView = inflater.inflate(R.layout.recycler_item, null);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position) == TYPE_HEADER || getItemViewType(position) == TYPE_FOOTER){
            return;
        }
        MyViewHolder myViewHolder = (MyViewHolder)holder;
        myViewHolder.textView.setText(datas.get(getRealPosition(position)));

        if(recyclerViewItemClick != null){
            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerViewItemClick.onItemClick(getRealPosition(position), position);
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        if(header == null && footer == null){
            return datas.size();
        }else if(header == null && footer != null){
            return datas.size() + 1;
        }else if(header != null && footer == null){
            return datas.size() + 1;
        }else {
            return datas.size() + 2;
        }
    }

    @Override
    // 针对每个item判断其type
    public int getItemViewType(int position) {
        if(header != null && position == 0){
            return TYPE_HEADER;
        }else if(footer != null && position == getItemCount() - 1){
            return TYPE_FOOTER;
        }
        return super.getItemViewType(position);
    }

    public int getRealPosition(int position){
        return header == null? position: position - 1;
    }

    public void setHeader(View header){
        this.header = header;
    }

    public void setFooter(View footer){
        this.footer = footer;
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.tv_adapter);
        }
    }

    // 设置点击事件
    private RecyclerViewItemClick recyclerViewItemClick;
    public void setRecyclerViewItemClick(RecyclerViewItemClick recyclerViewItemClick){
        this.recyclerViewItemClick = recyclerViewItemClick;
    }
    // 设置点击接口
    public interface RecyclerViewItemClick{
        void onItemClick(int realPosition, int position);
    }
}
