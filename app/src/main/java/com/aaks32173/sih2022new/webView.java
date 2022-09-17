package com.aaks32173.sih2022new;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class webView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        WebView w = (WebView) findViewById(R.id.web);
        String url = getIntent().getExtras().getString("url","www.google.com");
        w.loadUrl(url);
        w.getSettings().setJavaScriptEnabled(true);
        w.getSettings().setUseWideViewPort(false);
        w.setWebViewClient(new WebViewClient());

    }
}