package com.other.demo.observer1;

public class Demo {

    public static void main(String[] args) {

        Subject subject = new Subject();
        new BinaryObserver(subject);
        new HexaObserver(subject);

        subject.setState(12);
    }


}
