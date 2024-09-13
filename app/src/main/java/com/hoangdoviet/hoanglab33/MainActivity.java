package com.hoangdoviet.hoanglab33;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.hoangdoviet.hoanglab33.data.ArticleReposotory;
import com.hoangdoviet.hoanglab33.data.model.Article;
import com.hoangdoviet.hoanglab33.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ListArticle.View {
    private ListArticle.Presenter mPresenter;
    private ArticleAdapter alarmRecyclerViewAdapter;
    private ActivityMainBinding binding;
//    private BroadcastReceiver wifiReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//            NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
//            if (activeNetwork == null || !activeNetwork.isConnected()) {
//                Toast.makeText(context, "Hiện tin tức ngoại tuyến", Toast.LENGTH_LONG).show();
//            } else {
//                Toast.makeText(context, "Hiện tin tức trực tuyến", Toast.LENGTH_LONG).show();
//             }
//         }
//    };
//    @Override
//    public void onResume() {
//        super.onResume();
//        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
//        this.registerReceiver(wifiReceiver, filter);
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        this.unregisterReceiver(wifiReceiver);
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ArticleReposotory db = new ArticleReposotory(this.getApplication());
        mPresenter = new ListArticlePresenter (db, this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        alarmRecyclerViewAdapter = new ArticleAdapter();
        binding.recyclerView.setAdapter(alarmRecyclerViewAdapter);
        binding.main.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getData();
                binding.main.setRefreshing(false);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.getData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.clear();
    }

    @Override
    public void setArticle(List<Article> articleList) {
        alarmRecyclerViewAdapter.setAlarms(articleList);

    }

    @Override
    public void hideProgressBar() {
        binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setPresenter(ListArticle.Presenter presenter) {
        mPresenter = presenter;
    }
}