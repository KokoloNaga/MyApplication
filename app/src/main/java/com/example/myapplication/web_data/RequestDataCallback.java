package com.example.myapplication.web_data;

public abstract class RequestDataCallback<T> {
    // 返回json对象
    public void dataCallback(T obj){

    }
    // 返回http状态和json对象
    public void dataCallback(int status, T obj){
        dataCallback(obj);
    }

    // 返回http状态、json对象和http原始数据
    public void dataCallback(int status, T obj, byte[] body){
        dataCallback(status, obj);
    }

}
