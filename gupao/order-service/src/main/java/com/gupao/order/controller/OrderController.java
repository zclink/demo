package com.gupao.order.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/order")
    public String order(){
        System.out.println(port);
        return "all order" + port;
    }

}
