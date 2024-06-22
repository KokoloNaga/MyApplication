package com.example.myapplication.recyclerView;

// 针对瀑布流的数据结构
public class ItemData {
    private String content;
    private int height;

    public ItemData(){

    }

    public ItemData(String content, int height){
        this.content = content;
        this.height = height;
    }

    public String getContent(){
        return content;
    }

    public int getHeight() {
        return height;
    }

    public void setContent(String content){
        this.content = content;
    }

    public void setHeight(int height){
        this.height = height;
    }
}
