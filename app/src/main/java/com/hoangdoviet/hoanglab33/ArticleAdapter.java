package com.hoangdoviet.hoanglab33;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hoangdoviet.hoanglab33.data.model.Article;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {
    private List<Article> alarms;

    public ArticleAdapter() {
        this.alarms = new ArrayList<>();
    }

    @NonNull
    @Override
    public ArticleAdapter.ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ArticleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleAdapter.ArticleViewHolder holder, int position) {
        Article alarm = alarms.get(position);
        holder.bind(alarm);
    }

    @Override
    public int getItemCount() {
        return alarms.size();
    }
    public void setAlarms(List<Article> alarms) {
        this.alarms = alarms;
        notifyDataSetChanged();
    }

    public static class ArticleViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView headLine;
        private TextView newsPublicationTime;
        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
             image = itemView.findViewById(R.id.img);
             headLine = itemView.findViewById(R.id.news_title);
             newsPublicationTime = itemView.findViewById(R.id.news_publication_time);
        }
        public void bind(Article article){
            headLine.setText(article.getTitle());
            if(article.getUrlToImage()== null || article.getUrlToImage().isEmpty()){
                Glide.with(itemView).load(R.drawable.samplenews).into(image);
            }else {
                Glide.with(itemView).load(article.getUrlToImage()).into(image);
            }
            ZonedDateTime currentTimeInHours = Instant.now().atZone(ZoneId.of("Asia/Kolkata"));
            ZonedDateTime newsTimeInHours = Instant.parse(article.getPublishedAt()).atZone(ZoneId.of("Asia/Kolkata"));
            Duration hoursDifference = Duration.between(currentTimeInHours, newsTimeInHours);
            String hoursAgo = " " + hoursDifference.toHours() + " hour ago";
            newsPublicationTime.setText(hoursAgo);
        }
    }
}
