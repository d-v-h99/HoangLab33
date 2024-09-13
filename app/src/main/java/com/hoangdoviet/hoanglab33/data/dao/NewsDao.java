package com.hoangdoviet.hoanglab33.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.hoangdoviet.hoanglab33.data.model.Article;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
@Dao
public interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertNews(Article news);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertArticles(List<Article> articles);

    @Query("select * from article_table order by publishedAt")
    Single<List<Article>> getNewsFromDataBase();

    @Delete
    Completable deleteNews(Article news);
}
