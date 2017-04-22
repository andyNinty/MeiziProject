package com.example.andy.bottomnavbar.util;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * com.example.andy.bottomnavbar.util
 * Created by andy on 17-4-21.
 */

public class SchedulersCompat {

    public static <T> Observable.Transformer<T, T> applySchedulers() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
