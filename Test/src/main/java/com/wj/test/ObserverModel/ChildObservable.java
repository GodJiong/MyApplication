package com.wj.test.ObserverModel;

/**
 * Created by wangjiong on 2017/9/30.
 */

/**
 * 子类被观察者
 */
public class ChildObservable extends Observable {
    /**
     * 用来通知父类把所有观察者更新
     *
     * @param state 状态
     */
    public void change(String state) {
        //调用父类更新方法
        notifyObservers(state);
    }
}
