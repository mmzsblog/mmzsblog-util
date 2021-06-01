package com.java.mmzsblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 启动类
 */
@EnableSwagger2
@SpringBootApplication
public class SpringBootWebSocketApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebSocketApplication.class, args);
    }

}