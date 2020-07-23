package com.other.demo;

import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@Primary
public class OtherDemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(OtherDemoApplication.class, args);

        Car2 car2 = applicationContext.getBean("car2", Car2.class);

        System.out.println(car2);


    }


    @Bean(name = "car2")
    public Car2 getCar2(Car car){

        Car2 car2 = new Car2();

        car2.setName(car.getName());

        return car2;
    }

    @Data
    public class Car2 {
        String name;
    }



    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
