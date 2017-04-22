package com.example.andy.bottomnavbar.meizifragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.andy.bottomnavbar.R;
import com.example.andy.bottomnavbar.base.BaseFragment;
import com.example.andy.bottomnavbar.constant.Constant;
import com.example.andy.bottomnavbar.meizifragment.adapter.MeiziAdapter;
import com.example.andy.bottomnavbar.meizifragment.model.Meizi;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscription;

/**
 * com.example.andy.bottomnavbar
 * Created by andy on 17-4-20.
 */

public class MeiziFragment extends BaseFragment implements MeiziContract.View, MeiziAdapter.OnRecyclerViewItemClickListener {
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mRefreshLayout;
    private MeiziContract.MeiziPresenter mPresenter;
    private MeiziAdapter mAdapter;
    private List<Meizi> mMeizis;


    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meizi, container, false);
        ButterKnife.bind(this, view);
        mRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);
        mRefreshLayout.setRefreshing(true);
        mRefreshLayout.setEnabled(false);
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        mPresenter.init();
        mPresenter.reqMeiziData(1);
    }

    @Override
    public void setPresenter(MeiziContract.MeiziPresenter presenter) {
        mPresenter = presenter;

    }

    @Override
    public void showToast(String message) {
        mRefreshLayout.setRefreshing(false);
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public Context getCtx() {
        return getContext();
    }

    @Override
    public void showMeizi(List<Meizi> meizis) {
        mRefreshLayout.setRefreshing(false);
        mMeizis = meizis;
        mAdapter = new MeiziAdapter();
        mAdapter.addMeizi(getContext(), mMeizis);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnclickItemListener(this);

    }

    @Override
    public void setSubscription(Subscription s) {
        addSubscription(s);
    }

    @Override
    public void onItemClick(Meizi meizi, ImageView imageView) {
        Intent intent = new Intent();
        intent.putExtra(Constant.PIC_URL, meizi.getUrl());
        intent.putExtra(Constant.PIC_TIME, meizi.getCreatedAt());
        intent.setClass(getActivity(), MeiziDetailActivity.class);
        ActivityOptionsCompat options = ActivityOptionsCompat
                .makeSceneTransitionAnimation(getActivity(), imageView, Constant.PIC_ANIMATION);
        ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
    }
}
