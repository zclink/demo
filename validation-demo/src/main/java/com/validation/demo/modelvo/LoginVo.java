package com.validation.demo.modelvo;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class LoginVo implements Serializable {

    @NotNull(message = "aaaaa")
    private String name;

    private Integer age;

    @AssertTrue
    private Boolean isLogin ;


}
