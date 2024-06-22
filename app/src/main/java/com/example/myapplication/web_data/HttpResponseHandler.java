package com.example.myapplication.web_data;

import java.io.IOException;

import javax.security.auth.callback.Callback;

import okhttp3.Call;
import okhttp3.Headers;
import okhttp3.Response;
import okhttp3.internal.http2.Header;

// 针对call的回复设计实现Callback，对结果进行判断
public abstract class HttpResponseHandler implements Callback {
    public HttpResponseHandler(){

    }

    public void onFailure(Call call, IOException e){
        onFailure(-1, e.getMessage().getBytes());
    }

    public void onResponse(Call call, Response response) throws IOException{
        int code = response.code();  // 返回数据的状态码
        byte[] body = response.body().bytes();  // 返回数据的内容

        if(code > 299){
            onFailure(response.code(), body);
        } else {
            Headers headers = response.headers();
            Header[] hs = new Header[headers.size()];

            for(int i = 0;i < headers.size();i++){
                hs[i] = new Header(headers.name(i), headers.value(i));
            }

            onSuccess(code, hs, body);
        }

    }

    public abstract void onSuccess(int statusCode, Header[] headers, byte[] responseBody);

    public void onProgress(int bytesWritten, int totalSize){

    }


    private void onFailure(int status, byte[] data) {
    }
}
