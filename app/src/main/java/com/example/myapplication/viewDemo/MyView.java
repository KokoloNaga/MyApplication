package com.example.myapplication.viewDemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.myapplication.R;

import java.util.Random;
/*
自定义View以及自定义属性的应用
 */
public class MyView extends View {
    private MyThread myThread;
    private Paint paint;  // 画笔
    private RectF rectF = new RectF(0, 0, 390, 390);  // 四个值为左上右下四个坐标
    private int sweepAngle = 0;  // 弧的当前度数
    private int sweepAngleAdd = 20;  // 弧每次增加度数
    private Random random = new Random();
    private boolean running = true;  // 控制循环

    public MyView(Context context) {
        this(context, null);
    }
    // 继承
    public MyView(Context context, AttributeSet attrs){
        super(context, attrs);
        init(context, attrs);
    }
    // 初始化
    private void init(Context context, AttributeSet attrs){
        paint = new Paint();
        paint.setTextSize(60);
        // 设置自定义属性，使得它能够直接在xml中修改
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.customStyleView);
        sweepAngleAdd = typedArray.getInt(R.styleable.customStyleView_sweepAngleAdd, 0);
        typedArray.recycle();

    }
    // 画布画图
    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

        Log.i("MyView", "onDraw");
        if(myThread == null){
            myThread = new MyThread();
            myThread.start();
        }
        else {
            canvas.drawArc(rectF, 0, sweepAngle, true, paint);  // 画扇型
        }
    }

    // 创建子线程绘制ui
    private class MyThread extends Thread{
        @Override
        public void run() {
            super.run();

            while (running){
                logic();
                postInvalidate();  // 线程刷新View的操作，会调用onDraw
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

    protected void logic() {
        sweepAngle += sweepAngleAdd; // 增加弧度

        // 随机产生rgb值
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);

        paint.setARGB(255, r, g, b);

        if(sweepAngle >= 360){
            sweepAngle = 0;
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        running = false;  // 销毁线程

    }

    // 需要重写onMeasure方法，否则设置background会出错
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // 获取测量模式和大小，MeasureSpec为View内部的静态类
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        if (widthMode == MeasureSpec.EXACTLY){  // 精确值或者match_parent
            width = widthSize;
        } else { // wrap_parent
            width = (int) (getPaddingLeft() + getPaddingRight() + rectF.width()*2);
        }

        if (heightMode == MeasureSpec.EXACTLY){  // 精确值或者match_parent
            height = heightSize;
        } else { // wrap_parent
            height = (int) (getPaddingTop() + getPaddingBottom() + rectF.height()*2);
        }

        setMeasuredDimension(width, height);
    }
}

