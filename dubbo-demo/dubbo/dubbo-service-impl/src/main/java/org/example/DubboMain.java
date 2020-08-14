package org.example;

import org.apache.dubbo.container.Main;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class DubboMain {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:/META-INF/spring.xml");
        context.start();
        System.in.read();


//        Main.main(args);

    }


}
