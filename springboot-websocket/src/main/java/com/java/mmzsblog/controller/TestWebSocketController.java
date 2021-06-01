package com.java.mmzsblog.controller;

import com.java.mmzsblog.server.TestWebSocketServer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：created by mmzsblog.cn
 * @description：
 * @date ：created at 2021/06/01 11:05
 */
@Api(tags = "WebSocket-Test")
@RestController("notification")
public class TestWebSocketController {

    @GetMapping
    @ApiOperation("通知test")
    public void test(@RequestParam String merchantId){
        TestWebSocketServer.sendMessage(merchantId,"有新订单啦");
    }
}
