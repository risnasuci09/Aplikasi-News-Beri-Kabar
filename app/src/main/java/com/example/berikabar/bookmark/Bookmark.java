package com.example.berikabar.bookmark;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.berikabar.berita.BeritaSource;

@Entity(tableName = "tbnews")
public class Bookmark {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "source")
    public String source;

    @ColumnInfo(name = "url")
    public String url;

    @ColumnInfo(name = "urlToImage")
    public String urlToImage;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }
}
