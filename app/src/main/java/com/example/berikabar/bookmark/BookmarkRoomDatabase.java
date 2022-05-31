package com.example.berikabar.bookmark;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Bookmark.class}, version = 1, exportSchema = false)
public abstract class BookmarkRoomDatabase extends RoomDatabase {

    private static BookmarkRoomDatabase sInstance;
    public abstract BookmarkDao bookmarkDao();
    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    @VisibleForTesting
    public static final String DATABASE_NAME = "bookmark_database";
    private void setDatabaseCreated(){
        mIsDatabaseCreated.postValue(true);
    }

    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            setDatabaseCreated();
        }
    }

    public static BookmarkRoomDatabase buildDatabase(final Context context) {
        return Room.databaseBuilder(context, BookmarkRoomDatabase.class, DATABASE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        BookmarkRoomDatabase database = BookmarkRoomDatabase.getInstance(context);
                        database.setDatabaseCreated();
                    }
                }).allowMainThreadQueries().fallbackToDestructiveMigration().build();
    }

    public static BookmarkRoomDatabase getInstance(final Context context) {
        if (sInstance == null) {
            synchronized (BookmarkRoomDatabase.class) {
                if (sInstance == null) {
                    sInstance = buildDatabase(context);
                    sInstance.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }

}
