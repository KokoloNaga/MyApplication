package com.example.myapplication.activity;

import android.app.Activity;
import android.app.Application;

import java.util.Stack;

/*
由于一次性销毁所有Activity多少有点问题，因此利用栈进行逐个销毁
将Stack放入Application中，由于Application的生命周期与应用程序是绑定的，这样可以保证Activity的栈静态变量不会被系统回收
 */
public class FinishApplication extends Application {
    private static Stack<Activity> activityStack;

    public void addActivity(Activity activity){
        if(activityStack == null){
            activityStack = new Stack<>();  // 初始化栈
        }
        if(!activityStack.contains(activity)){  // 栈中不存在当前的Activity
            activityStack.add(activity);
        }
    }

    public Activity currentActivity(){
        Activity activity = activityStack.lastElement();  // 获取Activity栈顶
        return activity;
    }

    public void removeActivity(Activity activity){  // 删除栈内某个Activity
        if(activity != null && activityStack.contains(activity)){
            activityStack.remove(activity);
        }
    }


    public void finishAllActivity(){  // 结束所有activity
        for(int i = 0, size = activityStack.size();i < size;i++){
            if(activityStack.get(i) != null){
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();

    }
}
