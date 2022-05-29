package com.example.berikabar;

import static com.example.berikabar.BeritaUtils.dateTime;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.berikabar.entity.Berita;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BeritaAdapter extends RecyclerView.Adapter<BeritaAdapter.ViewHolder>{
    Context context;
    List<Berita> beritaList;


    public BeritaAdapter(Context context, List<Berita> beritaList) {
        this.context = context;
        this.beritaList = beritaList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_berita,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {


        final Berita berita = beritaList.get(position);

        String imageUrl = berita.getUrlToImage();
        String url = berita.getUrl();

        Picasso.with(context).load(imageUrl).into(holder.imageView);

        holder.tvTitle.setText(berita.getTitle());
        holder.tvSource.setText(berita.getSource().getName());
        holder.tvDate.setText("\u2022"+dateTime(berita.getPublishedAt()));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,BeritaDetail.class);
                intent.putExtra("title",berita.getTitle());
                intent.putExtra("source",berita.getSource().getName());
                intent.putExtra("time",dateTime(berita.getPublishedAt()));
                intent.putExtra("desc",berita.getDescription());
                intent.putExtra("imageUrl",berita.getUrlToImage());
                intent.putExtra("url",berita.getUrl());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return beritaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle,tvSource,tvDate;
        ImageView imageView;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvJudul);
            tvSource = itemView.findViewById(R.id.tvSumber);
            tvDate = itemView.findViewById(R.id.tvDate);
            imageView = itemView.findViewById(R.id.image);
            cardView = itemView.findViewById(R.id.cardView);

        }
    }

}
