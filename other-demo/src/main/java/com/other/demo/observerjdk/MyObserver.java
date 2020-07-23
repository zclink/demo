package com.other.demo.observerjdk;

import java.util.Observable;
import java.util.Observer;

/**
 * 观察者
 */
public class MyObserver implements Observer {
    //观察者名称
    private String name;

    public MyObserver(String name){
        this.name = name;
    }

    // 观察者必须要有一个通知方法
    @Override
    public void update(Observable o, Object arg) {
        // TODO 这里实现业务逻辑
        MySubject b = (MySubject) o;
        System.out.println(name + "收到通知:" + b.getState());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
