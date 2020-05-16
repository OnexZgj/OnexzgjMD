package com.onexzgj.onexzgjmd;

import android.content.Context;

public class OnexUtils {

    private static OnexUtils INSTANCE;

    private Context context;
    private OnexUtils(Context context) {
        this.context = context;
    }

    public static   OnexUtils getInstance(Context context) {
        if (INSTANCE==null){
            INSTANCE  = new OnexUtils(context);
        }
        return INSTANCE;
    }





}
