package com.example.myapplication.viewDemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;

/*
自定义ViewGroup，针对子View仅有leftMargin和rightMargin
 */
public class MyViewGroup extends ViewGroup {
    public MyViewGroup(Context context) {
        super(context);
    }

    public MyViewGroup(Context context, AttributeSet attrs){
        super(context, attrs);

    }

    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs){
        return new MyLayoutParams(getContext(), attrs);
    }

    public static class MyLayoutParams extends MarginLayoutParams{
        public static int POSITION_RIGHT = 1;  // 子View位于母View的最右
        public static int POSITION_BOTTOM = 2;  // 子View位于母View的最底

        public int position = -1;

        public MyLayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);

            TypedArray a = c.obtainStyledAttributes(attrs, R.styleable.CustomlayoutLP);
            position = a.getInt(R.styleable.CustomlayoutLP_layout_position, position);
            a.recycle();
        }

        public MyLayoutParams(int width, int height){
            super(width, height);
        }

        public MyLayoutParams(ViewGroup.LayoutParams source){
            super(source);

        }
    }
    // 设置母View的宽高
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 获取母宽高
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);

        // 测量所有子宽高
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        if(getChildCount() <= 0){  // 无子View的情况下
            setMeasuredDimension(0, 0);
        } else if(heightMode == MeasureSpec.AT_MOST && widthMode == MeasureSpec.AT_MOST){  // 母View宽高模式均为wrap_content
            // 这里的View是水平摆放，母View的宽即为所有子View宽的和，母View的高即为最高的子View高
            int measureWidth = 0;
            int maxMeasureHeight = 0;

            for(int i = 0;i < getChildCount();i++){
                View child = getChildAt(i);
                MyLayoutParams lp = (MyLayoutParams)child.getLayoutParams();
                measureWidth += lp.leftMargin + lp.rightMargin + child.getMeasuredWidth();

                if(child.getMeasuredHeight() > maxMeasureHeight){  // 选择最高的子View
                    maxMeasureHeight = child.getMeasuredHeight();
                }
            }
            setMeasuredDimension(measureWidth, maxMeasureHeight);
        }
        else {  // 非wrap_content的情况
            setMeasuredDimension(sizeWidth, sizeHeight);
        }

    }

    // 设置子View的位置
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        int left = 0;

        for(int i = 0;i < count;i++){
            View child = getChildAt(i);  // 遍历每个子View

            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();

            MyLayoutParams lp = (MyLayoutParams)child.getLayoutParams();  // 获得子View的MarginLayout参数

            // 适合
            if(lp.position == MyLayoutParams.POSITION_RIGHT){
                child.layout(getWidth() - childWidth, 0, getWidth(), childHeight);
            }else if(lp.position == MyLayoutParams.POSITION_BOTTOM){
                child.layout(left + lp.leftMargin, getHeight() - childHeight, left + childWidth + lp.leftMargin, getHeight());
            }else{
                child.layout(left + lp.leftMargin, 0, left + childWidth + lp.leftMargin, childHeight);
            }

            left += childWidth + lp.leftMargin + lp.rightMargin;
        }
    }
}
