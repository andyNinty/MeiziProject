package com.example.andy.meizi.meizifragment;

import com.example.andy.meizi.base.BasePresenter;
import com.example.andy.meizi.base.BaseView;
import com.example.andy.meizi.meizifragment.model.Meizi;

import java.util.List;

/**
 * 将view层的接口和presenter层的接口管理起来
 * Created by andy on 17-4-21.
 */

public interface MeiziContract {

    /**
     * 界面逻辑的处理
     */
    interface View extends BaseView<MeiziPresenter> {
        void showMeizi(List<Meizi> meizis);

    }

    /**
     * 业务逻辑的处理
     */
    interface MeiziPresenter extends BasePresenter {
        void reqMeiziData(int page);
    }

}
