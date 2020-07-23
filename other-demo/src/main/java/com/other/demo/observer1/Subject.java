package com.other.demo.observer1;

import java.util.ArrayList;
import java.util.List;

/**
 * 被观察对象
 */
public class Subject {
    // 观察者列表
    protected List<Observer> observers = new ArrayList<>();
    // 被观察的属性
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }


    public void attach(Observer observer){
        observers.add(observer);
    }

    public void notifyAllObservers(){
        for(Observer observer : observers){
            observer.update();
        }
    }


}
