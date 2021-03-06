package com.example.andy.meizi;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.andy.meizi.articlefragment.ArticleFragment;
import com.example.andy.meizi.articlefragment.ArticlePresenter;
import com.example.andy.meizi.meizifragment.MeiziFragment;
import com.example.andy.meizi.meizifragment.MeiziPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    private ViewPager mViewPager;
    private List<Fragment> mFragments;
    private BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        initView();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mViewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_dashboard:
                    mViewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_notifications:
                    mViewPager.setCurrentItem(mFragments.size() - 1);
                    return true;
            }
            return false;
        }

    };

    private void initView() {
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        MeiziFragment meiziFragment = new MeiziFragment();
        ArticleFragment articleFragment = new ArticleFragment();
        mViewPager = (ViewPager) findViewById(R.id.viewpage);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        if (mFragments == null) {
            mFragments = new ArrayList<>();
        }
        adapter.addFragment(meiziFragment);
        adapter.addFragment(articleFragment);
        adapter.addFragment(new NotificationFragment());

        new MeiziPresenter(meiziFragment);
        new ArticlePresenter(articleFragment);

        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(mFragments.size());
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int item = mViewPager.getCurrentItem();
                navigation.setSelectedItemId(PagerItem2NavItem(item));
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

//        mViewPager.setOnTouchListener((v, event) -> true);

    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {

        private ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        private void addFragment(Fragment fragment) {
            mFragments.add(fragment);
        }
    }

    /**
     * 将viewpager滑动的item 和BottomNavigationView 底部的item绑定
     *
     * @param item viewpager item
     * @return item
     */
    private int PagerItem2NavItem(int item) {
        switch (item) {
            case 0:
                return R.id.navigation_home;
            case 1:
                return R.id.navigation_dashboard;
            case 2:
                return R.id.navigation_notifications;
        }
        return 0;
    }

}
