package com.gupao.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Lesson32Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Lesson32Application.class, args);
        String[] beans = run.getBeanDefinitionNames();

        for(String bean : beans){
            System.out.println(bean);
        }

    }

}
