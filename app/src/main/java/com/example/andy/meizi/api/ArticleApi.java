package com.example.andy.meizi.api;

import com.example.andy.meizi.articlefragment.model.ArticleResponse;

import retrofit2.http.GET;
import rx.Observable;

/**
 * api
 * Created by andy on 17-4-24.
 */

public interface ArticleApi {

    /**
     * 每日一文api
     * @return article
     */
    @GET("article/today?dev=1")
    Observable<ArticleResponse> getArticle();
}
