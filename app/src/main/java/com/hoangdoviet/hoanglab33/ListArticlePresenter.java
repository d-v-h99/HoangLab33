package com.hoangdoviet.hoanglab33;

import android.util.Log;

import com.hoangdoviet.hoanglab33.data.ArticleReposotory;
import com.hoangdoviet.hoanglab33.data.model.Article;
import com.hoangdoviet.hoanglab33.util.Constant;

import io.reactivex.rxjava3.disposables.Disposable;

public class ListArticlePresenter implements ListArticle.Presenter{
    private final ArticleReposotory alarmRepository;
    private final ListArticle.View mView;
    private Disposable disposable;
    public ListArticlePresenter(ArticleReposotory alarmRepository, ListArticle.View mView) {
        this.alarmRepository = alarmRepository;
        this.mView = mView;
        this.mView.setPresenter(this);
    }

    @Override
    public void populateArticle() {
        disposable = alarmRepository.getArticles()
                .subscribe(
                        articles -> {
                            for (Article a: articles){
                                a.setUrlToImage(null);
                            }
                            mView.hideProgressBar();
                            mView.setArticle(articles);

                            },
                        throwable -> {  throwable.printStackTrace();}
                );

    }

    @Override
    public void clear() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    @Override
    public void getData() {
     disposable= alarmRepository.getArticlesFromApi("us", "business", Constant.API_KEY)
              .subscribe(
                      articles -> {
                          mView.hideProgressBar();
                          mView.setArticle(articles);
                          Log.d("API Success", "Articles retrieved and saved to DB, số lượng: " + articles.size());
                      },
                      throwable -> {
                          populateArticle();
                          Log.e("API Error", "Error fetching articles", throwable);
                      }
              );
    }
}
