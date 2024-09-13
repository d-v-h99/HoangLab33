package com.hoangdoviet.hoanglab33.data;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.hoangdoviet.hoanglab33.api.JsonPlaceHolderAPI;
import com.hoangdoviet.hoanglab33.api.RetrofitClient;
import com.hoangdoviet.hoanglab33.data.dao.NewsDao;
import com.hoangdoviet.hoanglab33.data.model.Article;
import com.hoangdoviet.hoanglab33.data.model.NewsResponse;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleReposotory {
    private final NewsDao newsDao;
    private JsonPlaceHolderAPI jsonPlaceHolderApi;

    public ArticleReposotory(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        newsDao = db.newsDao();
        jsonPlaceHolderApi = RetrofitClient.getJsonPlaceHolderAPI();
    }

    public Completable insert(Article article) {
        return newsDao.insertNews(article)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Completable delete(Article article) {
        return newsDao.deleteNews(article)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<List<Article>> getArticles() {
        return newsDao.getNewsFromDataBase()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public void saveArticlesToDb(List<Article> articles) {
//        for(Article a : articles){
//            a.setUrlToImage(null);
//        }
        newsDao.insertArticles(articles)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    // Thành công
                    Log.d("DB_INSERT", "Articles saved successfully");
                }, throwable -> {
                    // Xử lý lỗi
                    Log.e("DB_INSERT", "Error saving articles", throwable);
                });
    }

    //    public List<Article> getArticlesFromApi(String country, String category, String key){
//        Call<NewsResponse> call = jsonPlaceHolderApi.getNews(country, category, key);
//        call.enqueue(new Callback<NewsResponse>() {
//            @Override
//            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
//                if (!response.isSuccessful()) {
//                    Log.d("checkAPI", "Lỗi: " + response.code());
//                    return;
//                }
//                NewsResponse newsResponse = response.body();
//                if (newsResponse != null && newsResponse.getArticles() != null) {
//                    List<Article> articles = newsResponse.getArticles();
//                    // Lưu vào database
//                    new Thread(() -> newsDao.insertArticles(articles)).start();
//                    Log.d("checkAPI", "Lấy thành công " + articles.size() + " bài viết");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<NewsResponse> call, Throwable t) {
//
//            }
//        });
//        return null;
//
//    }
//    public void getArticlesFromApi(String country, String category, String key) {
//        jsonPlaceHolderApi.getNews(country, category, key)
//                .subscribeOn(Schedulers.io()) // Thực thi trên background thread
//                .observeOn(AndroidSchedulers.mainThread()) // Quan sát kết quả trên main thread
//                .flatMap(newsResponse -> {
//                    if (newsResponse != null && newsResponse.getArticles() != null) {
//                        // Lưu vào Room database
//                        saveArticlesToDb(newsResponse.getArticles());
//                        return Observable.just(newsResponse.getArticles()); // Trả về danh sách articles
//                    } else {
//                        return Observable.error(new Throwable("Không có dữ liệu từ API"));
//                    }
//                })
//                .subscribe(
//                        articles -> {
//                            // Thành công, cập nhật giao diện
//                            Log.d("API Success", "Articles retrieved and saved to DB");
//                        },
//                        throwable -> {
//                            // Xử lý lỗi
//                            Log.e("API Error", "Error fetching articles", throwable);
//                        }
//                );
//    }
    public Observable<List<Article>> getArticlesFromApi(String country, String category, String key){
        return jsonPlaceHolderApi.getNews(country, category, key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(newsResponse -> {
                    if (newsResponse != null && newsResponse.getArticles() != null) {
                        // Lưu vào Room database
                        saveArticlesToDb(newsResponse.getArticles());
                        return Observable.just(newsResponse.getArticles()); // Trả về danh sách articles
                    } else {
                        return Observable.error(new Throwable("Không có dữ liệu từ API"));
                    }
                });
    }

}
