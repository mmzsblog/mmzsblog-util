package com.java.mmzsblog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：liuhongjiang
 * @description：TODO
 * @date ：2020/1/16 16:09
 */
@RestController
public class testController {

//    @Resource
//    private DataSource dataSource;

    @GetMapping("/query")
    public void query(){
//        System.out.println("查询到的数据源连接池信息是:"+dataSource);
//        System.out.println("查询到的数据源连接池类型是:"+dataSource.getClass());
//        System.out.println("查询到的数据源连接池名字是:"+dataSource.getPoolProperties().getName());
        System.out.println("");
    }
}
