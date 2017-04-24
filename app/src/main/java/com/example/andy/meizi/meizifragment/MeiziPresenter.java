package com.example.andy.meizi.meizifragment;

import android.util.Log;

import com.example.andy.meizi.api.ApiFactory;
import com.example.andy.meizi.api.MeiziApi;
import com.example.andy.meizi.meizifragment.db.MeiziModel;
import com.example.andy.meizi.meizifragment.db.MeiziModel_Table;
import com.example.andy.meizi.meizifragment.model.Meizi;
import com.example.andy.meizi.util.SchedulersCompat;
import com.raizlabs.android.dbflow.runtime.TransactionManager;
import com.raizlabs.android.dbflow.runtime.transaction.process.ProcessModelInfo;
import com.raizlabs.android.dbflow.runtime.transaction.process.SaveModelTransaction;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;

/**
 * MeiziPresenter
 * Created by andy on 17-4-21.
 */

public class MeiziPresenter implements MeiziContract.MeiziPresenter {

    private MeiziContract.View mView;
    private MeiziApi meiziApi;
    private List<MeiziModel> meiziModels;


    /**
     * 将view和prsenter绑定
     *
     * @param view view
     */
    public MeiziPresenter(MeiziContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    /**
     * 初始化数据
     */
    @Override
    public void init() {
        meiziApi = ApiFactory.getApi().getMeiziApi();
    }

    /**
     * 请求数据
     *
     * @param page page
     */
    @Override
    public void reqMeiziData(int page) {
        Subscription subscription = meiziApi.getMeizi(10, page)
                .compose(SchedulersCompat.applySchedulers())
                .map(response -> {
                    if (!response.isError()) {
                        return response.getResults();
                    } else {
                        mView.showToast("网络请求失败");
                    }
                    return null;
                })
                .subscribe(meizis -> {
                    //先从数据库中查找
                    List<MeiziModel> mzms = new ArrayList<>();
                    //查找数据库中的第一条数据 作为判断后数据库是不是需要更新的条件 此处不能用时间更新
                    MeiziModel mzm = SQLite.select().from(MeiziModel.class).where(MeiziModel_Table.id.eq(1)).querySingle();
                    String url = mzm.getImgUrl();
                    meiziModels = SQLite.select().from(MeiziModel.class).queryList();
                    if (!meiziModels.isEmpty() && !meizis.get(0).getUrl().equals(url)) {
                        mView.showMeizi(meizis);
                        for (Meizi mm : meizis) {
                            MeiziModel meiziModel = new MeiziModel();
                            meiziModel.setImgUrl(mm.getUrl());
                            meiziModel.setCreateTime(mm.getCreatedAt());
                            mzms.add(meiziModel);
                        }
                        TransactionManager.getInstance()
                                .addTransaction(new SaveModelTransaction<>(ProcessModelInfo.withModels(mzms)));
                    } else {
                        loadfromDB();
                    }
                    Log.i("meiziPresenter", meizis.toString());
                }, throwable -> mView.showToast("失败 请重试"));
        mView.setSubscription(subscription);
    }

    private void loadfromDB() {
        List<Meizi> mms = new ArrayList<>();
        for (MeiziModel meiziModel : meiziModels) {
            Meizi meizi = new Meizi();
            meizi.setUrl(meiziModel.getImgUrl());
            meizi.setCreatedAt(meiziModel.getCreateTime());
            mms.add(meizi);
        }
        mView.showMeizi(mms);
    }

}
