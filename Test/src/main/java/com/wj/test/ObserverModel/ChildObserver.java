package com.wj.test.ObserverModel;

import android.util.Log;

/**
 * Created by wangjiong on 2017/9/30.
 */

public class ChildObserver implements Observer {
    @Override
    public void update(String state) {
        Log.e("ChildObserver-update:", "更新状态为" + state);
    }
}
