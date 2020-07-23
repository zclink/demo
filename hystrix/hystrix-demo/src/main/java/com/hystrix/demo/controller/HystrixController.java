package com.hystrix.demo.controller;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HystrixController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/he")
    @HystrixCommand(fallbackMethod = "hellofallBack")
    public String hello(){

        String url = "http://localhost:9091/user/user";
        String forObject = restTemplate.getForObject(url, String.class);

        return forObject;
    }

    public String hellofallBack(){

        return "hystrix fallback";
    }


}
