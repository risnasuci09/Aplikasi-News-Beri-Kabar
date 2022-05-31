package com.example.berikabar.bookmark;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;

import com.example.berikabar.berita.BeritaSource;

import java.util.List;

@Dao
public interface BookmarkDao {

    @Query("SELECT * FROM tbnews ")
    LiveData<List<Bookmark>> getAll();

    @Query("INSERT INTO tbnews (title,source,url,urlToImage) VALUES(:title,:source, :url, :imgUrl)")
    void insertBookmark(String title, String source, String url, String imgUrl);

    @Delete
    void delete(Bookmark Bookmark);
}
