package com.hoangdoviet.hoanglab33.data.model;

import java.util.List;

public class NewsResponse {
    private List<Article> articles;
    private String status;
    private int totalResults;

    public NewsResponse() {
    }

    public NewsResponse(List<Article> articles, String status, int totalResults) {
        this.articles = articles;
        this.status = status;
        this.totalResults = totalResults;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }
}
