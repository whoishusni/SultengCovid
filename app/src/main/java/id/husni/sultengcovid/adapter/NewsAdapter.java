/*
 * Copyright (c) 2020.
 * Made with ❤ by Moh Husni Mubaraq
 * Not For Commercial Purpose
 */

package id.husni.sultengcovid.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import id.husni.sultengcovid.R;
import id.husni.sultengcovid.model.News;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private final Context context;
    private final ArrayList<News> newsArrayList = new ArrayList<>();

    public NewsAdapter(Context context) {
        this.context = context;
    }

    public void setNewsArrayList(ArrayList<News> items) {
        if (newsArrayList.size() > 0) {
            newsArrayList.clear();
        }
        newsArrayList.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_item_holder, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
        holder.tvNewsTitle.setText(newsArrayList.get(position).getNewsTitle());
        holder.tvNewsSource.setText(newsArrayList.get(position).getSourceNews().sourceName);
        Glide.with(context)
                .load(newsArrayList.get(position).getNewsUrlToImage())
                .into(holder.newsImage);
        holder.cvNews.setOnClickListener(v -> {
            String url = newsArrayList.get(position).getNewsUrl();
            Uri uriNews = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW,uriNews);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return newsArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView tvNewsTitle;
        final TextView tvNewsSource;
        final ImageView newsImage;
        final CardView cvNews;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNewsTitle = itemView.findViewById(R.id.tvNewsTitle);
            tvNewsSource = itemView.findViewById(R.id.tvNewsSource);
            newsImage = itemView.findViewById(R.id.imageNews);
            cvNews = itemView.findViewById(R.id.cvNewsHolder);
        }
    }
}
