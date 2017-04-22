package com.example.andy.bottomnavbar.meizifragment;

import android.content.Context;

import com.example.andy.bottomnavbar.base.BasePresenter;
import com.example.andy.bottomnavbar.base.BaseView;
import com.example.andy.bottomnavbar.meizifragment.model.Meizi;

import java.util.List;

import rx.Subscription;

/**
 * com.example.andy.bottomnavbar.meizifragment
 * Created by andy on 17-4-21.
 */

public interface MeiziContract {

    /**
     * 界面逻辑的处理
     */
    interface View extends BaseView<MeiziPresenter> {
        Context getCtx();

        void showMeizi(List<Meizi> meizis);

        void setSubscription(Subscription s);


    }

    /**
     * 业务逻辑的处理
     */
    interface MeiziPresenter extends BasePresenter {
        void reqMeiziData(int page);
    }

}
