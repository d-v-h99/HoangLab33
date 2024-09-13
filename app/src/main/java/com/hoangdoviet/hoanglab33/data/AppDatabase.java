package com.hoangdoviet.hoanglab33.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.hoangdoviet.hoanglab33.data.dao.NewsDao;
import com.hoangdoviet.hoanglab33.data.model.Article;

@Database(entities = {Article.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;
    public abstract NewsDao newsDao();
    public static AppDatabase getDatabase(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "alarm")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
