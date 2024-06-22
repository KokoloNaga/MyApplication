package com.example.myapplication.web_data;

import java.util.ArrayList;
import java.util.List;

/*
对okhttp的封装
 */
public class HttpConfig {
    private boolean debug = false;  // 是否是debug模式
    private String userAgent = "";  // 用户代理，是服务器能够识别客户使用的os及版本、CPU类型、浏览器及版本、浏览器渲染引擎、浏览器语言、浏览器插件等
    private boolean agent = true;  // 有代理的情况下能否访问
    private String tagName = "Http";

    private int connectTimeout = 10;  // 连接超时时间
    private int writeTimeout = 10;  // 写入超时时间
    private int readTimeout = 30;  // 读取超时时间

    private List<NameValuePair> commonField = new ArrayList<>();

    // 基本方法
    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public List<NameValuePair> getCommonField() {
        return commonField;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    public int getWriteTimeout() {
        return writeTimeout;
    }

    public void setWriteTimeout(int writeTimeout) {
        this.writeTimeout = writeTimeout;
    }

    // 更新参数，将新类加入或更改进commonField中
    public void updateCommonField(String key, String value){
        boolean result = true;
        for(int i = 0;i < commonField.size();i++){
            NameValuePair nameValuePair = commonField.get(i);
            if(nameValuePair.getName().equals(key)){  // key相同时
                commonField.set(i, new NameValuePair(key, value));
                result = false;
                break;
            }
        }
        if(result){
            commonField.add(new NameValuePair(key, value));
        }
    }

    // 删除参数
    public void removeCommonField(String key){
        for (int i = commonField.size() - 1;i >= 0;i++){
            if(commonField.get(i).equals(key)){
                commonField.remove(i);
            }
        }
    }

    // 单纯添加参数
    public void addCommonField(String key, String value){
        commonField.add(new NameValuePair(key, value));
    }


}
