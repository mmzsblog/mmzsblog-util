package com.java.mmzsblog.server;

import com.java.mmzsblog.util.DateUtil;
import com.puwang.cloud.socket.service.BaseService;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author ：created by mmzsblog.cn
 * @description：
 * @date ：created at 2021/04/12 14:12
 */
@Slf4j
@Component
@Sharable
public class DiscardServerHandler extends SimpleChannelInboundHandler {
    @Resource
    private BaseService baseService;

    /**
     * 接收到的数据是字节流数据
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            ByteBuf in = (ByteBuf) msg;
            String content = in.toString(CharsetUtil.UTF_8);
            log.info("传输内容是: " + content);
            // 这里调用service服务,处理具体业务逻辑
            baseService.doSomeThing(content);
            // 响应客户端
            String responseStr = "在" + DateUtil.date2FormatString(new Date(), "yyyy-MM-dd HH:mm:ss")
                    + "我收到了来自" + ctx.channel().remoteAddress() + "的消息!";
//            ctx.channel().writeAndFlush(new TextWebSocketFrame(responseStr));
            // 响应：UnpooledHeapByteBuf(ridx: 0, widx: 65, cap: 65/65)
//            ByteBuf resp= Unpooled.copiedBuffer(responseStr.getBytes());
//            ctx.writeAndFlush(resp);
            // 响应：直接返回字符串
            ctx.writeAndFlush(responseStr.getBytes());
        } finally {
            /**initChannel
             netty提供的一个释放ByteBuf内存的方法(ReferenceCountUtil.release(msg);)，
             如果不采用这个，直接调用ByteBuf.release方法也可以，但是因为上面调用了writeAndFlush方法，
             已经将ByteBuf的refCnt置为0了，这个里面调用的时候又会在设置一次，但是发现已经为0了，
             所以就抛出的该异常。
             */
            //此处会引起 对象的生命周期由引用计数器控制异常分析:
            // io.netty.util.IllegalReferenceCountException refCnt: 0, decrement: 1
//            ReferenceCountUtil.release(msg);
        }
    }


    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        log.info("链接断开：{}", ctx.channel().remoteAddress());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        log.info("链接创建：{}", ctx.channel().remoteAddress());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 出现异常就关闭
        cause.printStackTrace();
        ctx.close();
    }
}