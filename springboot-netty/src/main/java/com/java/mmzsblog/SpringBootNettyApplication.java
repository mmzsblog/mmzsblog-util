package com.java.mmzsblog;

import com.java.mmzsblog.server.DiscardServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.Resource;

/**
 * 消息通知服务启动类
 */
@EnableCaching
@EnableScheduling
@SpringBootApplication(scanBasePackages = {"com.java.mmzsblog.*"})
@MapperScan("com.java.mmzsblog.dao")
public class SpringBootNettyApplication implements CommandLineRunner {
    @Resource
    private DiscardServer discardServer;

    @Value("${socket.server.port}")
    private Integer port;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootNettyApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // 设置socket服务服务端的端口号
        // 建议写到配置文件中
        discardServer.run(port);
    }
}