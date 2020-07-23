package com.gupao.user.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

//    @Autowired
//    private LoadBalancerClient loadBalancerClient;

    @GetMapping("userOrder")
    @HystrixCommand(fallbackMethod = "fallback")
    public String getUserOrder(){

//        ServiceInstance serviceInstance = loadBalancerClient.choose("order-service");
//        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/order";
//        String forObject = restTemplate.getForObject(url, String.class);
//        return "user:" + forObject;

        String forObject = restTemplate.getForObject("http://order-service/order", String.class);
        return "user:" + forObject;

//        String forObject = restTemplate.getForObject("http://localhost:8081/order", String.class);
//        return "user:" + forObject;
    }

    public String fallback(){

        System.out.println("fallback");
        return "eror";
    }



}
