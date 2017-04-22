package com.example.andy.bottomnavbar.base;

/**
 * baseView 绑定presenter
 * 用来处理每一个view都会执行的方法,比如显示toast等
 * Created by andy on 17-4-21.
 */

public interface BaseView<T> {

    void setPresenter(T presenter);

    void showToast(String message);

}
