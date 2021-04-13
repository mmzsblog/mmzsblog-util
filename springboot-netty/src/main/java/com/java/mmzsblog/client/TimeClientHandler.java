package com.java.mmzsblog.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ：created by mmzsblog.cn
 * @description：TODO
 * @date ：created at 2021/04/12 16:36
 */
@Slf4j
public class TimeClientHandler extends SimpleChannelInboundHandler {
    private String req;

    public TimeClientHandler() {
        req = "tmb00035ET3318/08/22 11:5704026.75,027.31,20.00,20.00$";
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf message = null;
        for (int i = 0; i < 100; i++) {
            message = Unpooled.buffer(("$" + i + req).getBytes().length);
            message.writeBytes(("$" + i + req).getBytes());
            ctx.writeAndFlush(message);
        }
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, Object msg) {
        try {
            ByteBuf in = (ByteBuf) msg;
            String content = in.toString(CharsetUtil.UTF_8);
            log.info("响应数据content：" + content);
            log.info("响应数据msg：" + msg);
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 出现异常就关闭
        cause.printStackTrace();
        ctx.close();
    }

}