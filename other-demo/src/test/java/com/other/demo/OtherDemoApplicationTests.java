package com.other.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@SpringBootTest
@ActiveProfiles("")
class OtherDemoApplicationTests {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    void test1(){
        String url = "https://www.baidu.com";
        String forObject = restTemplate.getForObject(url, String.class, new HashMap<>());
        System.out.println(forObject);
    }

}
