package com.example.myapplication.web_data;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkhttpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_okhttp);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        toUser();
        toListUser();
        toMapUser();
        user2Json();

    }

    // Gson的用法
    // 1.解析成对象
    private void toUser(){
        String jsonStr = "{'name':'Ansen', 'age':20}";
        Gson gson = new Gson();
        User user = gson.fromJson(jsonStr, User.class);
        Log.i("toUser", "parserObject user:" + user.toString());
    }
    // 2.解析成数组
    private void toListUser(){
        String jsonStr = "[{'name':'Uini', 'age':30},{'name':'Lina', 'age':10}]";
        Gson gson = new Gson();
        List<User> users = gson.fromJson(jsonStr, new TypeToken<List<User>>(){}.getType());
        for(int i = 0;i < users.size();i++){
            Log.i("toList", "parserObject user:" +users.get(i).toString());
        }
    }
    // 3.解析成Map，与解析成数组基本一致
    private void toMapUser(){
        String jsonStr = "{'1':{'name':'Uini', 'age':30},'2':{'name':'Lina', 'age':10}}";
        Gson gson = new Gson();
        Map<String, User> users = gson.fromJson(jsonStr, new TypeToken<Map<String, User>>(){}.getType());
        for(String key:users.keySet()){
            Log.i("toMap", "parseMap key:" + key + " user:" + users.get(key).toString());
        }
    }
    // 4.将对象解析成Json字符串
    private void user2Json(){
        User user = new User();
        user.setAge(111);
        user.setName("nime");
        Gson gson = new Gson();
        String json = gson.toJson(user);
        Log.i("toJson", "JsonStr:" + json);
    }

    private OkHttpClient client = new OkHttpClient();
    // 1.Okhttp的GET请求
    private void getUserInfo(){
        // 创建请求
        Request.Builder builder = new Request.Builder()
                .url("http://139.196.35.30:8080/OkhttpTestInfo.do");
        // 执行请求execute
        execute(builder);
    }
    // 2.Okhttp的POST请求
    private void login(){
        // 通过FormBody.Builder构建并封装成RequestBody，再由request.builder上传，与GET的唯一差别就在于是否加POST
        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("username", "ansen");
        formBuilder.add("password", "123");
        RequestBody requestBody = formBuilder.build();
        Request.Builder builder = new Request.Builder()
                .url("http://139.196.35.30:8080/OkhttpTestInfo.do").post(requestBody);

        execute(builder);

    }

    // 3.Okhttp文件传输
    private void uploadFile(Byte[] bytes){
        // 核心是MultipartBody
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.addFormDataPart("username", "ansen"); // 表单参数
        builder.addFormDataPart("password", "123456");  // 表单参数

        builder.setType(MultipartBody.FORM);

        MediaType mediaType = MediaType.parse("application/octet-stream");
        Byte[] fileBytes = bytes;
        builder.addFormDataPart("upload_file", "ansen.txt", RequestBody.create(mediaType, Arrays.toString(fileBytes)));


        RequestBody requestBody = builder.build();
        Request.Builder requestBuilder = new Request.Builder()
                .url("http://139.196.35.30:8080/OkhttpTestInfo.do")
                .post(requestBody);
        execute(requestBuilder);
    }


    private void execute(Request.Builder builder){
        Call call = client.newCall(builder.build());
        call.enqueue(callback);  // 加入调度队列
    }
    private Callback callback = new Callback() {
        @Override
        public void onFailure(@NonNull Call call, @NonNull IOException e) {
            Log.i("Activity", "onFailure");
            e.printStackTrace();
        }

        @Override
        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
            // 从服务器上得到的数据转换成utf-8的字符串
            String str = new String(response.body().bytes(), "utf-8");
            Log.i("Activity", "onResponse:" + str);

            Message message = handler.obtainMessage();
            message.obj = str;
            message.sendToTarget();
        }
    };

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            String result = (String) msg.obj;
            Log.i("get", result);
        }
    };

}