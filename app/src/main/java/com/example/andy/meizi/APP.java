package com.example.andy.meizi;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * com.example.andy.bottomnavbar
 * Created by andy on 17-4-21.
 */

public class APP extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(this);
    }
}
