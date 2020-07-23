package com.config.client.controller;

import com.config.client.config.GitAutoRefreshConfig;
import com.config.client.config.GitConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class TestController {

    @Autowired
    private GitConfig gitConfig;
    @Autowired
    private GitAutoRefreshConfig gitAutoRefreshConfig;

    @GetMapping("show")
    public Object show(){
        return gitConfig;
    }

    @GetMapping("autoShow")
    public Object autoShow(){
        return gitAutoRefreshConfig;
    }



}
