package com.app.service.controller;

import com.app.service.command.UserExceptionCommand;
import com.app.service.command.UserTimeOutCommand;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/command/timeout")
    public String commandTimeout() {
        return new UserTimeOutCommand().execute();
    }

    @RequestMapping("/command/exception")
    public String commandException() {
        return new UserExceptionCommand().execute();
    }
}
