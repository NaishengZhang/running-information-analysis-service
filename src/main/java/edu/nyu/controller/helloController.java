package edu.nyu.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloController {

//    @Value("${hello.msg}")
    public String msg;

    @RequestMapping("/hello")
    public String showMsg() {
        return this.msg;
    }
}
