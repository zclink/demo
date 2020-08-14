package com.example.service.impl;

import com.example.service.DemoService;
import org.apache.dubbo.config.annotation.Service;

@Service
public class DemoServiceImpl implements DemoService {
    @Override
    public String say() {

        return "hello word";
    }
}
