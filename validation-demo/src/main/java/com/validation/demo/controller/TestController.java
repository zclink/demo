package com.validation.demo.controller;

import com.validation.demo.modelvo.LoginVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@Slf4j
public class TestController {

    @PostMapping("login")
    public String login(@Valid @RequestBody LoginVo inVo,BindingResult result) throws Exception {
        if(result.hasErrors()){
            FieldError fieldError = result.getFieldError();
            if (fieldError != null) {
                log.error("error msg: 【{}】", fieldError.getDefaultMessage());
                System.out.println("error msg: 【{}】" + fieldError.getDefaultMessage());
            }
        }

//        throw new Exception("aaa");
        return inVo.toString();
    }

    @PostMapping("test")
    public String test(@RequestBody @Valid  @NotNull (message = "名字不能为空") String name, BindingResult bindResult){
        if(bindResult.hasErrors()){
            FieldError fieldError = bindResult.getFieldError();
            if (fieldError != null) {
                log.error("error msg: 【{}】", fieldError.getDefaultMessage());
            }
        }

        return name;
    }

}
