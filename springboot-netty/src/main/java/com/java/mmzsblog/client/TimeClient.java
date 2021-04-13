package com.java.mmzsblog.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author ：created by mmzsblog.cn
 * @description：测试
 * @date ：created at 2021/04/12 16:39
 */
public class TimeClient {




    public void connect(int port,String host)throws Exception{
        //配置客户端
        System.out.println(port+"--"+host);
        EventLoopGroup eventLoopGroup=new NioEventLoopGroup();
        try {
            Bootstrap b=new Bootstrap();
            b.group(eventLoopGroup).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            // 解法一：客户端在发送数据包的时候，每个包都固定长度，比如1024个字节大小，如果客户端发送的数据长度不足1024个字节，则通过补充空格的方式补全到指定长度；
                            // 对服务端发送的消息进行粘包和拆包处理，由于服务端发送的消息已经进行了空格补全，
//                            // 并且长度为20，因而这里指定的长度也为20
////                            socketChannel.pipeline().addLast(new FixedLengthFrameDecoder(20));
////                            // 将粘包和拆包处理得到的消息转换为字符串
////                            socketChannel.pipeline().addLast(new StringDecoder());
//                            // 对客户端发送的消息进行空格补全，保证其长度为20
////                            socketChannel.pipeline().addLast(new FixedLengthFrameEncoder(20));
//                            // 客户端发送消息给服务端，并且处理服务端响应的消息
//                            socketChannel.pipeline().addLast(new TimeClientHandler());

                            // 解法二：客户端在每个包的末尾使用固定的分隔符，例如\r\n，如果一个包被拆分了，则等待下一个包发送过来之后找到其中的\r\n，然后对其拆分后的头部部分与前一个包的剩余部分进行合并，这样就得到了一个完整的包；
//                            String delimiter = "$";
//                            // 对服务端返回的消息通过_$进行分隔，并且每次查找的最大大小为1024字节
//                            socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,
//                                    Unpooled.wrappedBuffer(delimiter.getBytes())));
//                            // 将分隔之后的字节数据转换为字符串
////                            socketChannel.pipeline().addLast(new StringDecoder());
//                            // 对客户端发送的数据进行编码，这里主要是在客户端发送的数据最后添加分隔符
//                            socketChannel.pipeline().addLast(new DelimiterBasedFrameEncoder(delimiter));
                            // 客户端发送数据给服务端，并且处理从服务端响应的数据
                            socketChannel.pipeline().addLast(new TimeClientHandler());
                        }
                    });
            //绑定端口，同步等待成功
            ChannelFuture f = b.connect(host,port).sync();
            //等待服务监听端口关闭
            f.channel().closeFuture().sync();
        }finally {
            //优雅退出，释放线程资源
            eventLoopGroup.shutdownGracefully();
        }
    }

}
