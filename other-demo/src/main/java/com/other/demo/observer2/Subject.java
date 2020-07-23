package com.other.demo.observer2;

import java.util.ArrayList;
import java.util.List;

/**
 * 被观察对象
 */
public class Subject{
    // 观察者列表
    protected List<Observer> observers = new ArrayList<>();
    // 被观察的属性
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        // 在被观察的对象发生变化的地方通知所有人
        notifyAllObservers();
    }


    public void attach(Observer observer){
        observers.add(observer);
    }

    //被观察者的通知所有人的方法 需要在事件发生时调用通知所有观察者
    public void notifyAllObservers(){
        for(Observer observer : observers){
            observer.update();
        }
    }


}
