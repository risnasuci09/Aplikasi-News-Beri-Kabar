package com.example.berikabar;

import static com.example.berikabar.BeritaUtils.dateTime;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.berikabar.berita.Berita;
import com.example.berikabar.bookmark.Bookmark;
import com.example.berikabar.bookmark.BookmarkRoomDatabase;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkAdapter.ViewHolder> {
    private List<Bookmark> bookmarkList;
    private Context context;
    private BookmarkRoomDatabase database;

    public BookmarkAdapter(List<Bookmark> bookmarkList, Context context) {
        this.bookmarkList = bookmarkList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_bookmark, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Bookmark bookmark = bookmarkList.get(position);
        String imageUrl = bookmark.getUrlToImage();
        String url = bookmark.getUrl();

        Picasso.with(context).load(imageUrl).into(holder.mImages);
        holder.mTitle.setText(bookmark.getTitle());
        holder.mSource.setText(bookmark.getSource());
        holder.mUrl.setText(bookmark.getUrl());

        holder.holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,BeritaWebView.class);
                intent.putExtra("title",bookmark.getTitle());
                intent.putExtra("source",bookmark.getSource());
                intent.putExtra("imageUrl",bookmark.getUrlToImage());
                intent.putExtra("url",bookmark.getUrl());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return bookmarkList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImages;
        private TextView mTitle, mSource, mUrl;
        private RelativeLayout holder;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImages = itemView.findViewById(R.id.img_avatar);
            mTitle = itemView.findViewById(R.id.tvTitle);
            mSource = itemView.findViewById(R.id.tvSource);
            mUrl = itemView.findViewById(R.id.tvUrl);
            holder = itemView.findViewById(R.id.listBookmark);

        }
    }
    public Bookmark getBookmarkAt(int position){
        return bookmarkList.get(position);
    }

}
