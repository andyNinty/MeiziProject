package com.example.andy.meizi.articlefragment;

import com.example.andy.meizi.api.ApiFactory;
import com.example.andy.meizi.api.ArticleApi;
import com.example.andy.meizi.articlefragment.model.ArticleResponse;
import com.example.andy.meizi.util.SchedulersCompat;

import rx.Subscription;

/**
 * 每日一文presenter
 * Created by andy on 17-4-24.
 */

public class ArticlePresenter implements ArticleContract.ArticlePresenter {

    private ArticleContract.View mView;
    private ArticleApi articleApi;

    public ArticlePresenter(ArticleContract.View mView) {
        this.mView = mView;
        mView.setPresenter(this);

    }

    @Override
    public void init() {
        articleApi = ApiFactory.getApi().getArticleApi();
    }

    @Override
    public void reqArticleData() {
        Subscription s = articleApi.getArticle()
                .compose(SchedulersCompat.applySchedulers())
                .map(ArticleResponse::getContent)
                .subscribe(content -> mView.showArticle(content), throwable -> mView.showToast("失败"));
        mView.setSubscription(s);


    }

    @Override
    public void save2DB() {

    }
}
