package com.other.demo.observer2;

public class Demo {

    public static void main(String[] args) {

        Subject subject = new Subject();
        new Observer(subject,"obA");
        new Observer(subject,"obB");

        subject.setState(12);
    }


}
