package com.example.andy.bottomnavbar.meizifragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.andy.bottomnavbar.R;
import com.example.andy.bottomnavbar.constant.Constant;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * meizi detail activity
 * Created by andy on 17-4-21.
 */

public class MeiziDetailActivity extends AppCompatActivity {
    @Bind(R.id.backdrop)
    ImageView mBackdrop;
    @Bind(R.id.toolbar)
    Toolbar toolbar;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meizi_detail);
        ButterKnife.bind(this);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }

        initAction();
    }

    private void initAction() {
        ViewCompat.setTransitionName(mBackdrop, Constant.PIC_ANIMATION);
        String url = getIntent().getStringExtra(Constant.PIC_URL);
        Glide.with(this).load(url).into(mBackdrop);
    }
}
