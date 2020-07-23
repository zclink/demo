package com.other.demo.observergua;

import com.google.common.eventbus.Subscribe;

/**
 * 观察者
 */
public class MyObserver   {
    //观察者名称
    private String name;

    public MyObserver(String name){
        this.name = name;
    }

    // 观察者必须要有一个通知方法
    @Subscribe
    public void update(String str) {
        // TODO 这里实现业务逻辑
        System.out.println(name + "收到通知:" + str);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
