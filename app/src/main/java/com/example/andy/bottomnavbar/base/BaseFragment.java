package com.example.andy.bottomnavbar.base;

import android.support.v4.app.Fragment;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * basefragment 封装事件链
 * Created by andy on 17-4-21.
 */

public class BaseFragment extends Fragment {
    public CompositeSubscription compositeSubscription = null;


    public void addSubscription(Subscription subscription) {
        if (compositeSubscription == null) {
            compositeSubscription = new CompositeSubscription();
        }
        compositeSubscription.add(subscription);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (compositeSubscription != null) {
            compositeSubscription.unsubscribe();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        compositeSubscription.unsubscribe();
    }
}
