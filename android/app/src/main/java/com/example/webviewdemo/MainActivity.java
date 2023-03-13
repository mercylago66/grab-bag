package com.example.webviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Array;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题栏
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//全屏
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.createWebView();
    }

    @SuppressLint("SetJavaScriptEnabled")
    protected void createWebView() {
        //  创建 webView 实例并通过 id 绑定我们刚刚再布局创建的 webview 标签
        //  R.id.webview 是 activity_main.xml 中的id
        final WebView webView = (WebView) findViewById(R.id.webview);

        // 设置 webview 允许执行 javascript 脚本
        webView.getSettings().setJavaScriptEnabled(true);

        // 确保跳转到另一个网页时仍然再 webview 中显示
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
//                Log.d(" url ", request.getUrl().toString());
                NetUtils.post("http://192.168.1.5:8888/", request.getUrl().toString());
                return null;
            }
        });

        // 加载指定网页
        String url = "https://demo.cqgame.games/Home";
        webView.loadUrl(url);
    }
}
