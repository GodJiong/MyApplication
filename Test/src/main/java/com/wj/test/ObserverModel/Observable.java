package com.wj.test.ObserverModel;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangjiong on 2017/9/30.
 */

public abstract class Observable {
    //保存每个observer
    private List<Observer> observerList = new ArrayList<>();

    /**
     * 增加一个观察者
     *
     * @param observer 观察者
     */
    public void add(Observer observer) {
        observerList.add(observer);
        Log.e("Observable-add: ", "新增一个观察者");
    }

    /**
     * 删除一个观察者
     *
     * @param observer 观察者
     */
    public void delete(Observer observer) {
        observerList.remove(observer);
        Log.e("Observable-delete:", "删除一个观察者");
    }

    /**
     * 消息通知
     *
     * @param state 状态
     */
    public void notifyObservers(String state) {
        for (Observer observer : observerList) {
            observer.update(state);
        }
    }
}
