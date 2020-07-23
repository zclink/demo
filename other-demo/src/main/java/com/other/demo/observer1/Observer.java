package com.other.demo.observer1;

/**
 * 观察者抽象类  只需要将观察者 和 被观察者建立联系就可以
 */
public abstract class Observer {

    protected Subject subject ;

    public abstract void update();




}
