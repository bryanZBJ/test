package com.example.consumer.controller;

import com.example.consumer.feign.HelloRemote;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "consumer", tags = "消费方")
public class ConsumerController {

    @Autowired
    private HelloRemote helloRemote;

    @RequestMapping(value = "/hello/{name}", method = RequestMethod.GET)
    @ApiOperation(value = "hello测试", notes = "testHello")
    @ApiImplicitParams({@ApiImplicitParam(name = "name", value = "name", required = true)})
    public String index(@PathVariable("name") String name) {
        return helloRemote.hello(name);
    }

    @GetMapping("/test")
    @ApiOperation(value = "test", notes = "返回hello")
    public String test() {
        return "hello";
    }
}
