package com.java.mmzsit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ：mmzsit
 * @description：启动类
 * @date ：2019/6/13 11:18
 */
@SpringBootApplication
@MapperScan({"com.java.mmzsit.dao"})
//@EnableAsync
public class SubTableApplication {
    public static void main(String[] args) {
        SpringApplication.run(SubTableApplication.class, args);
    }
}
