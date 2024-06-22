package com.example.myapplication.web_data;

import com.google.gson.Gson;

import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.internal.http2.Header;

public class HTTPCaller {
    private static HTTPCaller _instance = null;
    private OkHttpClient client;
    private Map<String, Call> requestHandleMap = null;  // url为key的请求
    private CacheControl cacheControl = null;   // 缓冲控制器
    private Gson gson = null;
    private HttpConfig httpConfig = new HttpConfig();  // 配置信息
    private HTTPCaller() {

    }

    public static HTTPCaller getInstance(){
        if(_instance == null){
            _instance = new HTTPCaller();
        }
        return _instance;
    }

    // 设置配置信息
    public void setHttpConfig(HttpConfig httpConfig) {
        this.httpConfig = httpConfig;

        client = new OkHttpClient.Builder()
                .connectTimeout(httpConfig.getConnectTimeout(), TimeUnit.SECONDS)
                .writeTimeout(httpConfig.getWriteTimeout(), TimeUnit.SECONDS)
                .readTimeout(httpConfig.getReadTimeout(), TimeUnit.SECONDS)
                .build();

        gson = new Gson();
        requestHandleMap = Collections.synchronizedMap(new WeakHashMap<String, Call>());
        cacheControl = new CacheControl.Builder().noStore().noCache().build();  // 不使用缓存

    }



    /*
    public <T> void get(Class<T> clazz, final String url, Header[] header, final RequestDataCallback<T> callback){
        this.get(clazz, url, header, callback, true);
    }
    public <T> void get(Class<T> clazz, final String url, Header[] header, final RequestDataCallback<T> callback, boolean autoCancel){
        if(checkAgent()){
            return;
        }
        add
    }

    private Call getBuilder(String url, Header[] header, HttpResponseHandler responseCallback){
        url = Util.getMosaicParameter(url, httpConfig.getCommonField());  // 拼接公共参数
        Request.Builder builder = new Request.Builder();
        builder.url(url);
        builder.get();
        return execute(builder, header, responseCallback);
    }
     */




}
