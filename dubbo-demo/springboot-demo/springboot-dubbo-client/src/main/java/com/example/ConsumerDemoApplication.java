package com.example;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@DubboComponentScan
public class ConsumerDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerDemoApplication.class, args);
    }

}
