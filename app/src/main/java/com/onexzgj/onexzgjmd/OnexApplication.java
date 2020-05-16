package com.onexzgj.onexzgjmd;

import android.app.Application;

import com.onexzgj.viewscreen.SensorsDataAPI;
import com.squareup.leakcanary.LeakCanary;


public class OnexApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);

        SensorsDataAPI.init(this);


    }
}
