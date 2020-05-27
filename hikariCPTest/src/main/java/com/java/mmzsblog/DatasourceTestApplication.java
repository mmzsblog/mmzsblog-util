package com.java.mmzsblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author ：mmzsblog
 * @description：启动类
 * @date ：2020/1/16 16:02
 */
@SpringBootApplication
//@MapperScan({"com.java.mmzsblog"})
@EnableScheduling
public class DatasourceTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(DatasourceTestApplication.class, args);
    }
}