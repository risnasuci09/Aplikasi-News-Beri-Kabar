package com.example.berikabar.bookmark;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.berikabar.berita.BeritaSource;

import java.util.List;

public class BookmarkModel extends AndroidViewModel {

    private BookmarkRepository bookmarkRepository;
    private final LiveData<List<Bookmark>> bookmarkAll;

    public BookmarkModel(@NonNull Application application) {
        super(application);
        bookmarkRepository = new BookmarkRepository(application);
        bookmarkAll = bookmarkRepository.getAllBookmark();
    }

   public LiveData<List<Bookmark>> getAllBeritaBookmark(){
        return bookmarkAll;
    }

    public void insertBookmark(String title, String source, String url, String imgUrl){
        bookmarkRepository.insertBookmark(title,source,url,imgUrl);
    }

    public void delete(Bookmark bookmark){
        bookmarkRepository.deleteOne(bookmark);
    }

}
