package com.example.andy.meizi.articlefragment;

import com.example.andy.meizi.articlefragment.model.Article;
import com.example.andy.meizi.articlefragment.model.ArticleResponse;
import com.example.andy.meizi.base.BasePresenter;
import com.example.andy.meizi.base.BaseView;

import java.util.List;

/**
 * com.example.andy.meizi.articlefragment
 * Created by andy on 17-4-24.
 */

public interface ArticleContract {

    interface View extends BaseView<ArticlePresenter> {
        void showArticle(ArticleResponse.Content content);

        void showArticleFromDB(List<Article> articles);

        void setBtnState(boolean isSuccess);


    }


    interface ArticlePresenter extends BasePresenter {
        void reqArticleData();

        void save2DB();

        void loadAllFromDB();

        void loadFromDB(int key);

        void isLikeSuccess();

        void delFromDB();

    }
}

