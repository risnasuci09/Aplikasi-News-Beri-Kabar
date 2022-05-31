package com.example.berikabar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.berikabar.berita.BeritaSource;
import com.example.berikabar.bookmark.Bookmark;
import com.example.berikabar.bookmark.BookmarkModel;
import com.squareup.picasso.Picasso;

public class BeritaDetail extends AppCompatActivity {
    TextView tvJudul,tvSumber,tvTanggal,tvAuthor,tvDeskripsi, tvUrl;
    String titleBerita,timeBerita,authorBerita,sumberBerita,deskripsiBerita,urlBerita,imageUrl;
    ImageView imageView;
    ImageButton btnBookmark;
    AppCompatButton btnCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berita_detail);
        tvJudul = findViewById(R.id.tvJudul);
        tvSumber = findViewById(R.id.tvSumber);
        tvTanggal = findViewById(R.id.tvDate);
        tvAuthor = findViewById(R.id.tvAuthor);
        tvDeskripsi = findViewById(R.id.tvDeskripsi);
        tvUrl = findViewById(R.id.tvUrl);
        imageView = findViewById(R.id.imageView);
        btnCheck = findViewById(R.id.buttonWebView);
        btnBookmark = findViewById(R.id.saveArticle);

        getData();
        setData();

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BeritaDetail.this, BeritaWebView.class).putExtra("url", urlBerita));

            }
        });

        btnBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent replyIntent = new Intent(BeritaDetail.this,BookmarkActivity.class);
                BookmarkModel viewModel = new ViewModelProvider(BeritaDetail.this).get(BookmarkModel.class);
                replyIntent.putExtra("title",titleBerita);
                replyIntent.putExtra("source",sumberBerita);
                replyIntent.putExtra("url",urlBerita);
                replyIntent.putExtra("imageUrl",imageUrl);
                viewModel.insertBookmark(titleBerita,sumberBerita,urlBerita,imageUrl);
                Toast.makeText(BeritaDetail.this,"Berita Berhasil di Bookmark", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void getData(){
        Intent intent = getIntent();
        titleBerita = intent.getStringExtra("title");
        sumberBerita = intent.getStringExtra("source");
        timeBerita = intent.getStringExtra("time");
        deskripsiBerita  = intent.getStringExtra("desc");
        authorBerita = intent.getStringExtra("author");
        imageUrl = intent.getStringExtra("imageUrl");
        urlBerita = intent.getStringExtra("url");

    }

    public void setData(){
        tvJudul.setText(titleBerita);
        tvTanggal.setText(timeBerita);
        tvAuthor.setText(authorBerita);
        tvSumber.setText(sumberBerita);
        tvDeskripsi.setText(deskripsiBerita);
        tvUrl.setText(urlBerita);
        Picasso.with(BeritaDetail.this).load(imageUrl).fit().into(imageView);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
