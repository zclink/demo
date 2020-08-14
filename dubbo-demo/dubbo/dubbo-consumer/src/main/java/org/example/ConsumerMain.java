package org.example;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConsumerMain {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring.xml");
        HelloService helloService = (HelloService)context.getBean("helloService");
        String say = helloService.say();
        System.out.println(say);
    }

}
