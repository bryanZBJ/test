package com.example.consumer.controller;

import com.example.consumer.feign.HelloRemote;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "consumer", tags = "消费方")
public class ConsumerController {

    @Value("${swagger.host}")
    private String data;

    @Value("${app.test}")
    private String appTest;

    @Autowired
    private HelloRemote helloRemote;

    @RequestMapping(value = "/hello/{name}", method = RequestMethod.GET)
    @ApiOperation(value = "hello测试", notes = "testHello")
    @ApiImplicitParams({@ApiImplicitParam(name = "name", value = "name", required = true)})
    public String index(@PathVariable("name") String name) {
        return helloRemote.hello(name);
    }

    @GetMapping("/test")
    @ApiOperation(value = "test", notes = "返回获取配置字段")
    public String test() {
        return data;
    }

    @GetMapping("/test/application")
    @ApiOperation(value = "test总配置", notes = "返回获取配置字段")
    public String appTest() {
        return appTest;
    }
}
