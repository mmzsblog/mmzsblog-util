package com.java.mmzsblog.server;

import com.java.mmzsblog.server.handler.ChildChannelHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author ：created by mmzsblog.cn
 * @description：TODO
 * @date ：created at 2021/04/12 14:11
 */
@Slf4j
@Component
public class DiscardServer {
    @Resource
    private ChildChannelHandler childChannelHandler;

    public void run(int port) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        log.info("准备运行端口：" + port);
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childHandler(childChannelHandler);
            // 绑定端口，同步等待成功
            ChannelFuture future = bootstrap.bind(port).sync();
            log.info("启动 Netty 成功!");
            // 等待服务监听端口关闭
            future.channel().closeFuture().sync();
        } finally {
            // 退出，释放线程资源
            workerGroup.shutdownGracefully().syncUninterruptibly();
            bossGroup.shutdownGracefully().syncUninterruptibly();
            log.info("关闭 Netty 成功!");
        }
    }


}