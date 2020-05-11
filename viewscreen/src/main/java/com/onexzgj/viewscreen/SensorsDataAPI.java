package com.onexzgj.viewscreen;

import android.app.Application;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

public class SensorsDataAPI {

    private static final Object mLock = new Object();

    private static SensorsDataAPI INSTANCE;
    private String mDeviceId;
    private static Map<String, Object> mDeviceInfo;
    private final String TAG = this.getClass().getSimpleName();

    public static final String SDK_VERSION = "1.0.0";

    public static SensorsDataAPI getInstance() {
        return INSTANCE;
    }

    public static SensorsDataAPI init(Application application) {
        synchronized (mLock) {
            if (null == INSTANCE) {
                INSTANCE = new SensorsDataAPI(application);
            }
            return INSTANCE;
        }
    }

    public SensorsDataAPI(Application application) {
        mDeviceId = SensorsDataPrivate.getAndroidID(application.getApplicationContext());
        mDeviceInfo = SensorsDataPrivate.getDeviceInfo(application.getApplicationContext());

//        SensorsDataPrivate.registerActivityLifecycleCallbacks(application);
        WindowCallback.registerActivityLifecycleCallbacks(application);

    }


    public void track(String eventName, JSONObject properties) {
        try {
            JSONObject jsonObject = new JSONObject();

            jsonObject.put("event", eventName);
            jsonObject.put("device_id", mDeviceId);

            JSONObject sendProperties = new JSONObject(mDeviceInfo);

            if (properties != null) {
                SensorsDataPrivate.mergeJSONObject(properties, sendProperties);
            }


            jsonObject.put("properties", sendProperties);
            jsonObject.put("time", System.currentTimeMillis());
            Log.i(TAG, SensorsDataPrivate.formatJson(jsonObject.toString()));

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


}
