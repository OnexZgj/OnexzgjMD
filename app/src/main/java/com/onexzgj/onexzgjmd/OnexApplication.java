package com.onexzgj.onexzgjmd;

import android.app.Application;

import com.onexzgj.viewscreen.SensorsDataAPI;

public class OnexApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SensorsDataAPI.init(this);
    }
}
