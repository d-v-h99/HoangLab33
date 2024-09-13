package com.hoangdoviet.hoanglab33.api;

import com.hoangdoviet.hoanglab33.data.model.NewsResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JsonPlaceHolderAPI {
    @GET("v2/top-headlines")
    Observable<NewsResponse> getBreakingNews(
            @Query("country") String countryCode,
            @Query("page") int pageNumber,
            @Query("apiKey") String apiKey
    );
    @GET("v2/top-headlines")
    Observable<NewsResponse> getNews(
            @Query("country") String country,
            @Query("category") String category,
            @Query("apiKey") String key
    );
}
