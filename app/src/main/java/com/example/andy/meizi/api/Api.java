package com.example.andy.meizi.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * api用来管理和创建请求
 * Created by andy on 17-4-21.
 */

public class Api {

    private MeiziApi meiziApi = null;
    private ArticleApi articleApi = null;

    Api() {
        Retrofit retrofit = new Retrofit.Builder()
                .client(new OkHttpClient())
                .baseUrl(NetConfig.MEI_ZI_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        meiziApi = retrofit.create(MeiziApi.class);

        articleApi = new Retrofit.Builder()
                .client(new OkHttpClient())
                .baseUrl(NetConfig.ONE_ARTICLE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build().create(ArticleApi.class);
    }

    public MeiziApi getMeiziApi() {
        return meiziApi;
    }

    public ArticleApi getArticleApi() {
        return articleApi;
    }
}
