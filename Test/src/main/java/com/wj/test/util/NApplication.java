package com.wj.test.util;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by wangjiong on 2017/9/17.
 */

public class NApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
