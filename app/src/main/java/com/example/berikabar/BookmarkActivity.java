package com.example.berikabar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.berikabar.bookmark.Bookmark;
import com.example.berikabar.bookmark.BookmarkModel;
import com.example.berikabar.bookmark.BookmarkRoomDatabase;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class BookmarkActivity extends AppCompatActivity {
    private List<Bookmark> list = new ArrayList<>();
    private BookmarkModel mBookmarkViewModel;
    private BookmarkRoomDatabase database;
    private BookmarkAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);
        mBookmarkViewModel = new ViewModelProvider(this,new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(BookmarkModel.class);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.bookmark);

        database = BookmarkRoomDatabase.getInstance(getApplicationContext());
        mBookmarkViewModel.getAllBeritaBookmark().observe(this,Bookmarks ->{
            list = Bookmarks;
            adapter = new BookmarkAdapter(list, BookmarkActivity.this);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            recyclerView.setAdapter(adapter);
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.bookmark:
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

            new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT| ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                mBookmarkViewModel.delete(adapter.getBookmarkAt(viewHolder.getAdapterPosition()));
                adapter.notifyDataSetChanged();
                Toast.makeText(BookmarkActivity.this, "Bookmark Dihapus", Toast.LENGTH_SHORT).show();

            }
        }).attachToRecyclerView(recyclerView);



    }
}
