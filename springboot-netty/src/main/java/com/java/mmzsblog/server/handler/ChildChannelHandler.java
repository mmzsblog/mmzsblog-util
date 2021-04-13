package com.java.mmzsblog.server.handler;

import com.java.mmzsblog.server.DiscardServerHandler;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author ：created by mmzsblog.cn
 * @description：处理粘包和拆包问题
 * @date ：created at 2021/04/12 14:11
 */
@Component
public class ChildChannelHandler extends ChannelInitializer<SocketChannel> {

    // 配置文件中事先定义好的分隔符号
    @Value("${socket.server.split}")
    private String split;

    @Resource
    private DiscardServerHandler discardServerHandler;

    /**
     * 未处理粘包和拆包问题
     *  处理粘包和拆包问题的四种方案：https://www.mmzsblog.cn/articles/2021/04/13/1618284054945.html
     */
//    @Override
//    public void initChannel(SocketChannel socketChannel) throws Exception {
//        socketChannel.pipeline().addLast(discardServerHandler);
//    }

    /**
     * 解法二：客户端在每个包的末尾使用固定的分隔符，例如\r\n，如果一个包被拆分了，
     *      则等待下一个包发送过来之后找到其中的\r\n，然后对其拆分后的头部部分与前一个包的剩余部分进行合并，这样就得到了一个完整的包；
     *
     * @param ch
     * @throws Exception
     */
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        // 将delimiter设置到DelimiterBasedFrameDecoder中，经过该解码一器进行处理之后，源数据将会
        // 被按照_$进行分隔，这里1024指的是分隔的最大长度，即当读取到1024个字节的数据之后，若还是未
        // 读取到分隔符，则舍弃当前数据段，因为其很有可能是由于码流紊乱造成的
        ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,
                Unpooled.wrappedBuffer(split.getBytes())));
        // 将分隔之后的字节数据转换为字符串数据
//        ch.pipeline().addLast(new StringDecoder());
        // 这是我们自定义的一个编码器，主要作用是在返回的响应数据最后添加分隔符
//        ch.pipeline().addLast(new DelimiterBasedFrameEncoder(delimiter));


        // 最终处理数据并且返回响应的handler
        ch.pipeline().addLast(discardServerHandler);
    }

    /**
     * 解法一：客户端在发送数据包的时候，每个包都固定长度，比如1024个字节大小，
     *      如果客户端发送的数据长度不足1024个字节，则通过补充空格的方式补全到指定长度；
     *  此方法遇到不定长时的情况则无法处理
     * @param socketChannel
     * @throws Exception
     */
//    @Override
//    protected void initChannel(SocketChannel socketChannel) throws Exception {
//        int length = 20;
//        // 将前一步解码得到的数据转码为字符串
////        socketChannel.pipeline().addLast(new StringDecoder());
//        // 这里FixedLengthFrameEncoder是我们自定义的，用于将长度不足1024的消息进行补全空格
//        socketChannel.pipeline().addLast(new FixedLengthFrameEncoder(length));
//        // 最终的数据处理
//        socketChannel.pipeline().addLast(discardServerHandler);
//    }

}