package com.example.producer.controller;

import com.example.common.exception.BizException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${hello}")
    private String value;

    @RequestMapping("/config")
    public String testConfig() {
        return value;
    }

    @RequestMapping("/hello")
    public String index(@RequestParam String name) {
        return "hello " + name + "，this is first messge";
    }

    @RequestMapping("/test")
    public String test(@RequestParam String name) {
        throw new BizException("1000", "异常");
    }
}
