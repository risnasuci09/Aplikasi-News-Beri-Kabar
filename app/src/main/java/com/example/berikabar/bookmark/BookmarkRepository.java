package com.example.berikabar.bookmark;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.berikabar.berita.BeritaSource;

import java.util.List;

public class BookmarkRepository {
    private BookmarkDao bookmarkDao;
    private LiveData<List<Bookmark>> bookmarkAll;

      BookmarkRepository(Application application){
          BookmarkRoomDatabase db = BookmarkRoomDatabase.getInstance(application);
          bookmarkDao = db.bookmarkDao();
          bookmarkAll = bookmarkDao.getAll();
      }
      public LiveData<List<Bookmark>> getAllBookmark(){
          return bookmarkAll;
      }

    void insertBookmark(String title, String source, String url, String imgUrl){
        BookmarkRoomDatabase.databaseWriteExecutor.execute(()->{
            bookmarkDao.insertBookmark(title,source,url,imgUrl);
        });
    }

    void deleteOne(Bookmark bookmark){
        BookmarkRoomDatabase.databaseWriteExecutor.execute(()->{
            bookmarkDao.delete(bookmark);
        });
    }


}
