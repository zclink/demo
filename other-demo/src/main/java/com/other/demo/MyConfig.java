package com.other.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class MyConfig {



    @Bean
    public Car getCar(){
        Car car = new Car();
        car.setName("aaaaaa");

        return car;
    }


}
