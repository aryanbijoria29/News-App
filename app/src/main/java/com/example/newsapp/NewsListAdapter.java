package com.example.newsapp;

import android.hardware.biometrics.BiometricManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class NewsListAdapter extends RecyclerView.Adapter<NewsViewHolder> {

    private ArrayList<News> items=new ArrayList<>();
    private NewsItemClicked listener;

    public NewsListAdapter(NewsItemClicked listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.news_items, parent,false);
        NewsViewHolder viewHolder =new NewsViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 listener.onItemClicked(items.get(viewHolder.getAdapterPosition()));
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        News currentItem = items.get(position);
        holder.titleView.setText(currentItem.title);
        holder.author.setText(currentItem.author);
        Glide.with(holder.itemView.getContext()).load(currentItem.imageUrl).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void updateNews(ArrayList<News> updatedNews){
         items.clear();
         items.addAll(updatedNews);
         notifyDataSetChanged();
    }

}

class NewsViewHolder extends RecyclerView.ViewHolder{
    TextView titleView;
    ImageView image;
    TextView author;

    public NewsViewHolder(View itemView) {
        super(itemView);
        titleView = itemView.findViewById(R.id.title);
        image = itemView.findViewById(R.id.image);
        author = itemView.findViewById(R.id.author);
    }
}

interface NewsItemClicked{
    void onItemClicked(News item);
}


