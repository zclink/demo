package com.boot.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    @PostMapping("/test")
    public String getStr(){
        return "aaaaa";
    }


}
