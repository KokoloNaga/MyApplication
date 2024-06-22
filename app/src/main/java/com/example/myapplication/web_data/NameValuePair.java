package com.example.myapplication.web_data;

public class NameValuePair {
    private String name;
    private String value;
    private boolean isFile = false;  // 是否是文件
    public NameValuePair(String name, String value){
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isFile() {
        return isFile;
    }

    public void setFile(boolean file) {
        isFile = file;
    }
}
