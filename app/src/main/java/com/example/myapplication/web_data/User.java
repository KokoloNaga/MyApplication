package com.example.myapplication.web_data;

import androidx.annotation.NonNull;

public class User {
    private String name;
    private int age;
    public String getName(){
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @NonNull
    @Override
    public String toString() {  // 对象转换成json
        return "User{" + "name='" + name + '\'' + ", age=" + age + '}';
    }
}
