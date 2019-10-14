package com.example.producer.controller;

import com.example.common.exception.BizException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "helloController", tags = {"Hello"})
public class HelloController {

    @Value("${mes.test:这个是默认值}")
    private String value;

    @RequestMapping(value = "/config", method = RequestMethod.GET)
    @ApiOperation(value = "config测试", notes = "testConfig")
    public String testConfig() {
        return value;
    }

    @GetMapping("/hello")
    @ApiOperation(value = "hello测试", notes = "testHello")
    @ApiImplicitParams({@ApiImplicitParam(name = "name", value = "name", required = true)})
    public String index(@RequestParam String name) {
        return "hello " + name + "，this is first messge";
    }

    @RequestMapping("/test")
    public String test(@RequestParam String name) {
        throw new BizException("1000", "异常");
    }
}
