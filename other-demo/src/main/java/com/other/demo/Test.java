package com.other.demo;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

public class Test {

    public static void main(String[] args) {
        test3();
    }

    /**
     * 简单get
     */
    public static void test1(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://www.baidu.com";
        String forObject = restTemplate.getForObject(url, String.class);
        System.out.println(forObject);
    }

    /**
     * 简单get
     */
    public static void test2(){
        RestTemplateBuilder builder = new RestTemplateBuilder();
        RestTemplate restTemplate = builder.setConnectTimeout(Duration.ofSeconds(30))
                .setReadTimeout(Duration.ofSeconds(30))
                .build();
        String url = "https://www.baidu.com";
        String forObject = restTemplate.getForObject(url, String.class);
        System.out.println(forObject);
    }

    public static  void test3(){
        String url = "https://www.baidu.com";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("apiToken", "apiToken");

        // 防止中文乱码
        MediaType mediaType = MediaType.parseMediaType("application/json; charset=UTF-8");

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity = new HttpEntity(httpHeaders);
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
        System.out.println(exchange);
    }




}
