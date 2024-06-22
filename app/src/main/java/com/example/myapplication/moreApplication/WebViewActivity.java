package com.example.myapplication.moreApplication;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

public class WebViewActivity extends AppCompatActivity {
    private WebView webView;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_web_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        webView = findViewById(R.id.wb_test);
        progressBar = findViewById(R.id.progressbar);

        webView.addJavascriptInterface(WebViewActivity.this, "android");  // 添加js监听
        webView.setWebViewClient(webViewClient);
        webView.setWebChromeClient(webChromeClient);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);     // webview允许使用js
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);    // 设置缓存，这里是不使用缓存

        // 支持屏幕缩放
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
    }

    // 处理WebView的各种通知和请求事件
    private WebViewClient webViewClient = new WebViewClient(){
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            // 页面加载完成
            progressBar.setVisibility(View.GONE);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            // 页面加载开始
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // 拦截url
            Log.i("ansen", "拦截url:" + url);
            if(url.equals("http://www.google.com/")){
                Toast.makeText(WebViewActivity.this, "拦截Google", Toast.LENGTH_LONG).show();
                return true;
            }
            return super.shouldOverrideUrlLoading(view, url);
        }
    };

    // 处理JS，网络图标，网络title
    private WebChromeClient webChromeClient = new WebChromeClient(){
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            // 监听JS的alert弹窗，通过自己的dialog弹窗
            AlertDialog.Builder builder = new AlertDialog.Builder(webView.getContext());
            builder.setMessage(message).setPositiveButton("确定", null);
            builder.setCancelable(false);
            builder.create().show();

            // 表示处理结果为确定状态并唤醒WebCore线程
            result.confirm();
            return true;
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            // 设置网页标题
            Log.i("ansen", "网页标题："+title);
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            // 加载进度回调
            progressBar.setProgress(newProgress);
        }

    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(webView.canGoBack() && keyCode == KeyEvent.KEYCODE_BACK){
            // 判断webview是否还可返回，即有没有上一页
            webView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);

    }


    @JavascriptInterface
    public void getClient(String str){
        Log.i("ansen", "html调用客户端：" + str);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        webView.destroy();
        webView = null;
    }
}