package com.wj.test.ObserverModel;

/**
 * Created by wangjiong on 2017/9/30.
 */

/**
 * 观察者接口类
 */
public interface Observer {
    /**
     * 通知更新（被观察者内部操作）
     *
     * @param state 状态
     */
    void update(String state);
}
