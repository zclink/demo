package com.resttemplate.demo;

import org.springframework.web.client.RestTemplate;

import java.net.URI;

public class Test {
    public static void main(String[] args) {
        String url = "https://www.baidu.com";
        RestTemplate restTemplate = new RestTemplate();
        URI uri = restTemplate.postForLocation(url, "");
        System.out.println(uri);




        //        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
//        System.out.println(forEntity);

    }

}
