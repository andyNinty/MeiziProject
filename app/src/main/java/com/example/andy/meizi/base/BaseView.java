package com.example.andy.meizi.base;

import android.content.Context;

import rx.Subscription;

/**
 * baseView 绑定presenter
 * 用来处理每一个view都会执行的方法,比如显示toast等
 * Created by andy on 17-4-21.
 */

public interface BaseView<T> {

    Context getCtx();

    void setPresenter(T presenter);

    void showToast(String message);

    void setSubscription(Subscription s);

}
