package com.other.demo.observer2;

/**
 * 观察者抽象类  只需要将观察者 和 被观察者建立联系就可以
 */
public class Observer {

    protected Subject subject ;
    // 观察者名称
    private String name;

    public Observer(Subject subject,String name){
        this.subject = subject;
        this.name = name;
        // 这里让观察者与被观察者建立联系
        this.subject.attach(this);
    }

    // 观察者必须要有一个通知方法
    public void update(){
        // TODO 这里实现业务逻辑
        System.out.println( name + "收到更新：" + subject.getState() );
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
