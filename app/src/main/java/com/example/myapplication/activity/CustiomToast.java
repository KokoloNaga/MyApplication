package com.example.myapplication.activity;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;

public class CustiomToast {
    private static CustiomToast _instance = null;
    private Toast toast = null;

    private final int MARGIN_DP = 50;

    private CustiomToast(){

    }

    public static CustiomToast getInstance(){
        if(_instance == null){
            _instance = new CustiomToast();
        }
        return _instance;
    }

    public void cancel(){
        if(toast != null){
            toast.cancel();
            toast = null;
        }
    }

    public void showToastCustom(Context context, String msg, int gravity){
        showToastCustom(context, msg, R.layout.toast_msg, R.id.txt_toast_message, gravity);
    }

    public void showToastCustom(Context context, String msg, int layoutResId, int txtResId, int gravity){
        cancel();
        try{
            if(TextUtils.isEmpty(msg)){  // 判断字符串是否为空
                return;
            }
            // 获得Toast所在的xml对应的View
            View layout = View.inflate(context, layoutResId, null);
            TextView txtMsg = layout.findViewById(txtResId);
            txtMsg.setText(msg);
            toast = new Toast(context);
            toast.setDuration(Toast.LENGTH_SHORT);
            // 安排Toast出现的位置，setGravity对应模式，x方向偏移，y方向偏移
            if(gravity == Gravity.TOP){
                int marginVertical = (int) dip2px(context, MARGIN_DP);
                toast.setGravity(gravity, 0, marginVertical);
            } else if(gravity == Gravity.BOTTOM){
                int marginVertical = (int) dip2px(context, MARGIN_DP);
                toast.setGravity(gravity, 0, marginVertical);
            } else {
                toast.setGravity(gravity, 0, 0);
            }
            toast.setView(layout);
            toast.show();
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public static float dip2px(Context context, float dpValue){
        // 将dp值转换为px值，针对不同的屏幕分辨率
        final float scale = context.getResources().getDisplayMetrics().density;
        float result = dpValue * scale + 0.5f;
        return result;
    }


}
