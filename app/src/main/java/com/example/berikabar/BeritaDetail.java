package com.example.berikabar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class BeritaDetail extends AppCompatActivity {
    TextView tvJudul,tvSumber,tvTanggal,tvDeskripsi;
    ImageView imageView;
    WebView webView;
    ProgressBar loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berita_detail);
        tvJudul = findViewById(R.id.tvJudul);
        tvSumber = findViewById(R.id.tvSumber);
        tvTanggal = findViewById(R.id.tvDate);
        tvDeskripsi = findViewById(R.id.tvDeskripsi);

        imageView = findViewById(R.id.imageView);

        webView = findViewById(R.id.webView);

        loader = findViewById(R.id.webViewLoader);
        loader.setVisibility(View.VISIBLE);

        Intent intent = getIntent();
        String judul = intent.getStringExtra("judul");
        String sumber = intent.getStringExtra("sumber");
        String tanggal = intent.getStringExtra("tanggal");
        String deskripsi = intent.getStringExtra("deskripsi");
        String imageUrl = intent.getStringExtra("imageUrl");
        String url = intent.getStringExtra("url");

        tvJudul.setText(judul);
        tvSumber.setText(sumber);
        tvTanggal.setText(tanggal);
        tvDeskripsi.setText(deskripsi);

        Picasso.with(BeritaDetail.this).load(imageUrl).into(imageView);

        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
        if (webView.isShown()){
            loader.setVisibility(View.INVISIBLE);
        }
    }
}
