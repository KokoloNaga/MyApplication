package com.example.myapplication.recyclerView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.myapplication.R;

public class DividerGridItemDecoration extends RecyclerView.ItemDecoration {
    /*
    排法，厚度，画笔
     */
    private int mOrientation = LinearLayoutManager.VERTICAL;
    private int mItemSize = 1;
    private Paint mPaint;

    public DividerGridItemDecoration(Context context){
        this(context, LinearLayoutManager.VERTICAL, R.color.purple_200, 1);
    }
    public DividerGridItemDecoration(Context context, int orientation){
        this(context, orientation, R.color.purple_200, 1);
    }
    public DividerGridItemDecoration(Context context, int orientation, int dividerColor){
        this(context, orientation, dividerColor, 1);
    }

    /**
     * @param context
     * @param orientation
     * @param dividerColor
     * @param mItemSize
     */
    public DividerGridItemDecoration(Context context, int orientation, int dividerColor, int mItemSize){
        this.mOrientation = orientation;
        if(orientation != LinearLayoutManager.VERTICAL && orientation != LinearLayoutManager.HORIZONTAL){
            throw new IllegalArgumentException("请传入正确的参数");  // 因orientation错误而报错
        }

        // 将dp值换算成px值
        this.mItemSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, mItemSize, context.getResources().getDisplayMetrics());
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);  // 画笔抗锯齿
        mPaint.setColor(context.getResources().getColor(dividerColor));
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        drawHorizontal(c, parent);
        drawVertical(c, parent);

    }

    // 获得列数，根据lauout的不同
    private int getSpanCount(RecyclerView parent){
        // 列数
        int spanCount = -1;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        // 确定当前为网格排布
        if(layoutManager instanceof GridLayoutManager){
            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        }else if(layoutManager instanceof StaggeredGridLayoutManager){  // 当前为瀑布流的情况
            spanCount = ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
        }
        return spanCount;
    }

    public void drawHorizontal(Canvas canvas, RecyclerView parent){
        int childCount = parent.getChildCount();
        for (int i = 0;i < childCount;i++){
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getLeft() - layoutParams.leftMargin;
            final int right = child.getRight() + layoutParams.rightMargin + mItemSize;
            final int top = child.getBottom() + layoutParams.bottomMargin;
            final int bottom = top + mItemSize;
            canvas.drawRect(left, top, right, bottom, mPaint);
        }
    }

    public void drawVertical(Canvas canvas, RecyclerView parent){
        int childCount = parent.getChildCount();
        for (int i = 0;i < childCount;i++){
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getRight() + layoutParams.rightMargin;
            final int right = left + mItemSize;
            final int top = child.getTop() - layoutParams.topMargin;
            final int bottom = child.getBottom() + layoutParams.bottomMargin;
            canvas.drawRect(left, top, right, bottom, mPaint);
        }
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int spanCount = getSpanCount(parent);
        int childCount = parent.getAdapter().getItemCount();

        if(isLastRow(parent, view.getId(), spanCount, childCount)){
            outRect.set(0, 0, mItemSize, 0);
        } else if(isLastColumn(parent, view.getId(), spanCount, childCount)){
            outRect.set(0, 0, 0, mItemSize);
        } else {
            outRect.set(0, 0, mItemSize, mItemSize);
        }

    }


    /**
     * @param parent 母recyclerView
     * @param pos 当前子View是第几个
     * @param spanCount 行数
     * @param childCount 总共子View的个数
     * @return
     */
    private boolean isLastColumn(RecyclerView parent, int pos, int spanCount, int childCount){
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if(layoutManager instanceof GridLayoutManager){
            if((pos + 1) % spanCount == 0){
                return true;
            }
        } else if(layoutManager instanceof StaggeredGridLayoutManager){
            int orientation = ((StaggeredGridLayoutManager) layoutManager).getOrientation();
            if(orientation == StaggeredGridLayoutManager.VERTICAL){
                if((pos + 1) % spanCount == 0){
                    return true;
                }
            } else {  // 瀑布流的水平摆放，最后一列就是最后几个View
                childCount = childCount - childCount % spanCount;
                if(pos >= childCount){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @param parent 母recyclerView
     * @param pos 当前子View是第几个
     * @param spanCount 行数
     * @param childCount 总共子View的个数
     * @return
     */
    private boolean isLastRow(RecyclerView parent, int pos, int spanCount, int childCount){
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if(layoutManager instanceof GridLayoutManager){
            childCount = childCount - childCount % spanCount;
            if(pos >= childCount){
                return true;
            }
        } else if(layoutManager instanceof StaggeredGridLayoutManager){
            int orientation = ((StaggeredGridLayoutManager) layoutManager).getOrientation();
            if(orientation == StaggeredGridLayoutManager.VERTICAL){
                childCount = childCount - childCount % spanCount;
                if(pos >= childCount) {
                    return true;
                }
            } else {
                if((pos + 1) % spanCount == 0){
                    return true;
                }
            }
        }
        return false;

    }
}
