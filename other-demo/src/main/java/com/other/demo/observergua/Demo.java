package com.other.demo.observergua;

import com.google.common.eventbus.EventBus;

public class Demo {

    public static void main(String[] args) {

        MyObserver oba = new MyObserver("oba");
        MyObserver obb = new MyObserver("obb");
        EventBus eventBus = new EventBus();
        eventBus.register(oba);
        eventBus.register(obb);

        eventBus.post("open");
    }
}
