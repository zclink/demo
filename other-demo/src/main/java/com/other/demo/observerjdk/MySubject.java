package com.other.demo.observerjdk;

import java.util.Observable;

/**
 * 被观察者
 */
public class MySubject extends Observable {


    // 被观察属性
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        // 标识事件发生了
        setChanged();
        // 在被观察对象发生变化时 通知所有观察者
        notifyObservers();
    }

}
