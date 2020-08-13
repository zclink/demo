package com.validation.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ValidationDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ValidationDemoApplication.class, args);
    }

//    @Bean
//    public MethodValidationPostProcessor methodValidationPostProcessor() {
//        return new MethodValidationPostProcessor();
//    }

}
