package com.example.myapplication.recyclerView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class DividerItemDecoration extends RecyclerView.ItemDecoration {
    private int mOrientation = LinearLayoutManager.VERTICAL;  // 纵向布局
    private int mItemSize = 1;  // 分隔线厚度，px值，设为1
    private Paint mPaint;  // 绘制分隔线的画笔

    // 以下三个构造方法重载
    public DividerItemDecoration(Context context){
        this(context, LinearLayoutManager.VERTICAL, R.color.purple_200, 1);
    }
    public DividerItemDecoration(Context context, int orientation){
        this(context, orientation, R.color.purple_200, 1);
    }
    public DividerItemDecoration(Context context, int orientation, int dividerColor){
        this(context, orientation, dividerColor, 1);
    }

    /**
     * @param context
     * @param orientation 绘制方向
     * @param dividerColor 分割线颜色所在的资源id
     * @param mItemSize 分割线宽度，输入为dp值
     */
    public DividerItemDecoration(Context context, int orientation, int dividerColor, int mItemSize){
        this.mOrientation = orientation;
        if(orientation != LinearLayoutManager.VERTICAL && orientation != LinearLayoutManager.HORIZONTAL){
            throw new IllegalArgumentException("请传入正确的参数");  // 因orientation错误而报错
        }
        // 将dp值换算成px值
        this.mItemSize = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, mItemSize, context.getResources().getDisplayMetrics());
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);  // 画笔抗锯齿
        mPaint.setColor(context.getResources().getColor(dividerColor));

    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);

        if(mOrientation == LinearLayoutManager.VERTICAL){
            drawVertical(c, parent);
        }else {
            drawHorizontal(c, parent);
        }
    }

    // 绘制纵向分隔线
    private void drawVertical(Canvas canvas, RecyclerView parent){
        final int left = 0;  // Item左起始位置
        final int right = parent.getMeasuredWidth() - parent.getPaddingRight();  // Item右终止位置
        final int childSize = parent.getChildCount();
        for(int i = 0;i < childSize;i++){
            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getTop() + layoutParams.topMargin;  // 针对有TopMargin的情况，分隔线的top
            final int bottom = top + mItemSize;  // 分隔线的底部
            // 画分隔线，本质上是画矩形
            Log.i("left", String.valueOf(left));
            canvas.drawRect(left, top, right, bottom, mPaint);
        }
    }
    // 绘制横向分隔线
    private void drawHorizontal(Canvas canvas, RecyclerView parent){
        final int top = parent.getPaddingTop();  // Item上起始位置
        final int bottom = parent.getMeasuredHeight() - parent.getPaddingBottom();  // Item下终止位置
        final int childSize = parent.getChildCount();
        for(int i = 0;i < childSize;i++){
            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getRight() + layoutParams.rightMargin;  // 针对有rightMargin的情况，分隔线的left
            final int right = left + mItemSize;  // 分隔线的right
            // 画分隔线，本质上是画矩形
            canvas.drawRect(left, top, right, bottom, mPaint);
        }
    }

    // 设置分隔线的属性
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        if(mOrientation == LinearLayoutManager.VERTICAL){
            outRect.set(0,0,0, mItemSize);  // 向下偏移
        }else {
            outRect.set(0,0, mItemSize, 0);  // 向右偏移
        }
    }
}
