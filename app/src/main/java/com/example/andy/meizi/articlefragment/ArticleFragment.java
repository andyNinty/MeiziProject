package com.example.andy.meizi.articlefragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andy.meizi.R;
import com.example.andy.meizi.articlefragment.model.ArticleResponse;
import com.example.andy.meizi.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscription;

/**
 * 每日一文
 * Created by andy on 17-4-20.
 */

public class ArticleFragment extends BaseFragment implements ArticleContract.View {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_author)
    TextView tvAuthor;
    @Bind(R.id.tv_content)
    TextView tvContent;
    private ArticleContract.ArticlePresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.init();
        mPresenter.reqArticleData();
    }

    @Override
    public Context getCtx() {
        return getContext();
    }

    @Override
    public void setPresenter(ArticleContract.ArticlePresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void setSubscription(Subscription s) {
        addSubscription(s);

    }

    @Override
    public void showArticle(ArticleResponse.Content response) {
        tvTitle.setText(response.getTitle());
        tvAuthor.setText(response.getAuthor());
        tvContent.setText(Html.fromHtml(response.getContent()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
