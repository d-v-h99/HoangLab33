package com.hoangdoviet.hoanglab33.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "article_table")
public class Article {
    //    @PrimaryKey(autoGenerate = true)
//    private int id ;
    @PrimaryKey
    @NonNull
    private String url;
    @Ignore
    private String author;
    private String content;
    private String description;
    private String publishedAt;
    private String title;
    private String urlToImage;
    @Ignore
    private Source source;

    public Article() {
    }

    public Article(@NonNull String url, String author, String content, String description, String publishedAt, String title, String urlToImage, Source source) {
        this.url = url;
        this.author = author;
        this.content = content;
        this.description = description;
        this.publishedAt = publishedAt;
        this.title = title;
        this.urlToImage = urlToImage;
        this.source = source;
    }

    @NonNull
    public String getUrl() {
        return url;
    }

    public void setUrl(@NonNull String url) {
        this.url = url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }
}
