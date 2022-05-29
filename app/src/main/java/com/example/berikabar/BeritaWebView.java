package com.example.berikabar;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class BeritaWebView extends AppCompatActivity {
    WebView webView;
    ProgressBar loader;
    String url;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berita_web_view);

        loader = findViewById(R.id.webViewLoader);
        webView = (WebView) findViewById(R.id.webView);
        loader.setMax(100);
        url =getIntent().getStringExtra("url");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
        loader.setMax(0);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String newUrl) {
                view.loadUrl(newUrl);
                loader.setProgress(0);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String urlStart, Bitmap favicon) {
                url = urlStart;
                invalidateOptionsMenu();
            }

            @Override
            public void onPageFinished(WebView view, String urlPage) {
                loader.setVisibility(View.GONE);
                invalidateOptionsMenu();
            }
        });
    }
}
