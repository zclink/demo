package com.other.demo.observer1;


public class BinaryObserver extends Observer {

    public BinaryObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("BinaryObserver 收到更新：" + subject.getState() );

    }
}
