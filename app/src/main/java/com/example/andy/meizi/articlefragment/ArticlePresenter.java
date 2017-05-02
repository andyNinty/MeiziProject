package com.example.andy.meizi.articlefragment;

import com.example.andy.meizi.api.ApiFactory;
import com.example.andy.meizi.api.ArticleApi;
import com.example.andy.meizi.articlefragment.db.ArticleModel;
import com.example.andy.meizi.articlefragment.db.ArticleModel_Table;
import com.example.andy.meizi.articlefragment.model.Article;
import com.example.andy.meizi.articlefragment.model.ArticleResponse;
import com.example.andy.meizi.constant.Constant;
import com.example.andy.meizi.util.SchedulersCompat;
import com.raizlabs.android.dbflow.runtime.transaction.process.ProcessModelInfo;
import com.raizlabs.android.dbflow.runtime.transaction.process.SaveModelTransaction;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

import cn.andyleeblog.customutils.SharedPreferencesUtil;
import rx.Subscription;

/**
 * 每日一文presenter
 * Created by andy on 17-4-24.
 */

public class ArticlePresenter implements ArticleContract.ArticlePresenter {

    private ArticleContract.View mView;
    private ArticleApi articleApi;
    private ArticleResponse.Content mContent;

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
                .map(response -> {
                    mContent = response.getContent();
                    return response.getContent();
                })
                .subscribe(content -> mView.showArticle(content), throwable -> mView.showToast("失败"));
        mView.setSubscription(s);


    }

    @Override
    public void save2DB() {
        //这里的收藏不涉及服务器 就是简单的保存到本地的数据库中
        //想了想 这里只是为了搞明白DBFlow的使用 实际开发中收藏还是要和服务器结合 这样效果才会比较好
        //这里只是用作简单Demo使用
        ArticleModel articleModel = new ArticleModel();
        articleModel.setId(mContent.getWc());
        articleModel.setAuthor(mContent.getAuthor());
        articleModel.setContent(mContent.getContent());
        articleModel.setTitle(mContent.getTitle());
//        //异步保存 立刻查询可能无法查到结果
//        TransactionManager.getInstance()
//                .addTransaction(new SaveModelTransaction<>(ProcessModelInfo.withModels(articleModel)));
        //实时保存 立刻保存
        new SaveModelTransaction<>(ProcessModelInfo.withModels(articleModel)).onExecute();
        ArticleModel article = SQLite.select(ArticleModel_Table.id)
                .from(ArticleModel.class).where(ArticleModel_Table.id.is(mContent.getWc())).querySingle();
        if (article != null) {
            mView.setBtnState(true);
            SharedPreferencesUtil.getInstance(mView.getCtx()).setBoolean(Constant.IS_SUCCESS, true);
            mView.showToast("收藏成功");
        } else {
            mView.setBtnState(false);
            SharedPreferencesUtil.getInstance(mView.getCtx()).setBoolean(Constant.IS_SUCCESS, false);
            mView.showToast("收藏失败");
        }

    }

    @Override
    public void loadAllFromDB() {
        List<Article> articles = new ArrayList<>();
        List<ArticleModel> articleModels = SQLite.select().from(ArticleModel.class).queryList();
        for (ArticleModel ams : articleModels) {
            Article article = new Article();
            article.setTitle(ams.getTitle());
            article.setAuthor(ams.getAuthor());
            article.setContent(ams.getContent());
            articles.add(article);
        }
        mView.showArticleFromDB(articles);
    }

    @Override
    public void loadFromDB(int key) {
        // TODO: 17-4-25 从数据库中 查询某一条数据
    }

    @Override
    public void isLikeSuccess() {
        boolean isSuccess = SharedPreferencesUtil.getInstance(mView.getCtx()).getBoolean(Constant.IS_SUCCESS, false);
        if (isSuccess) {
            mView.setBtnState(true);
        } else {
            mView.setBtnState(false);
        }
    }

    @Override
    public void delFromDB() {
        //删除
        SQLite.delete().from(ArticleModel.class).where(ArticleModel_Table.id.is(mContent.getWc())).execute();
        ArticleModel article1 = SQLite.select()
                .from(ArticleModel.class).where(ArticleModel_Table.id.is(mContent.getWc())).querySingle();
        if (article1 == null) {
            mView.setBtnState(false);
        } else {
            mView.setBtnState(true);
        }
    }
}
