package com.other.demo.observerjdk;

public class Demo {

    public static void main(String[] args) {
        MySubject mySubject = new MySubject();
        MyObserver oba = new MyObserver("oba");
        MyObserver obb = new MyObserver("obb");
        mySubject.addObserver(oba);
        mySubject.addObserver(obb);
        mySubject.setState(18);
    }
}
