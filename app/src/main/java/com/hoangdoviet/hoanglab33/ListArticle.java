package com.hoangdoviet.hoanglab33;

import com.hoangdoviet.hoanglab33.data.model.Article;
import com.hoangdoviet.hoanglab33.databinding.ActivityMainBinding;

import java.util.List;

public interface ListArticle {
    interface Presenter extends BasePresenter{
        void populateArticle();
        void clear();
        void getData();
    }
    interface View extends BaseView<ListArticle.Presenter>{
        void setArticle(List<Article> articleList);
        void hideProgressBar();
    }
}
