package com.other.demo.observer1;


public class HexaObserver extends Observer {

    public HexaObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("HexaObserver 收到更新：" + subject.getState() );

    }
}
