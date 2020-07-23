package com.eurka.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {

    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/hello")
    public String hello() {

        String url = "http://eurka-client/hello";
        String forObject = restTemplate.getForObject(url, String.class);

        System.out.println(forObject);

        return forObject;
    }
}
