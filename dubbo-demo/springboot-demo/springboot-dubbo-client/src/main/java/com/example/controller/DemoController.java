package com.example.controller;

import com.example.service.DemoService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
public class DemoController {

    @Reference(url = "dubbo://127.0.0.1:9091/com.example.service.DemoService")
    private DemoService demoService;

    @RequestMapping("say")
    public String say() {
        return demoService.say();
    }


}
