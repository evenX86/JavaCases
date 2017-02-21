package com.book.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.internal.SystemPropertyUtil;

/**
 * netty客户端
 * Created by xuyifei on 2017/2/14.
 */
public class TimeClient {
    public void connect(int port,String host) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new TimeClientHandler());
                        }
                    });
            System.out.println("发起连接");
            ChannelFuture f = b.connect(host,port).sync();//发起异步连接操作
            System.out.println("连接完成");
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }

    }
    public static void main(String[] args) throws InterruptedException {
        int port = 8088;
        new TimeClient().connect(port,"127.0.0.1");

    }
}
