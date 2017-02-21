package com.book.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * netty 示例 时间服务器服务器端.
 * Created by xuyifei on 2017/2/14.
 */
public class TimeServer {
    public void bind(int port) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();//包含了一组nio线程的线程组,专门用来网络事件的处理,实际上就是reactor线程组
        EventLoopGroup workerGroup = new NioEventLoopGroup();//前面试用来服务端接收客户端请求,这个用来处理socketchannel的网络读写
        try {
            ServerBootstrap b = new ServerBootstrap();//NIO服务端的辅助启动端
            b.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,1024)
                    .childHandler(new ChildChannelHandler());
            ChannelFuture f = b.bind(port).sync(); //同步阻塞方法sync,等待连接完成
            f.channel().closeFuture().sync(); // 等待服务器监听端口关闭,等待服务器链路关闭后,main才退出
        } finally {
            bossGroup.shutdownGracefully(); // 释放相关资源,优雅退出
            workerGroup.shutdownGracefully();
        }
    }

    /**
     * IO事件处理类
     * 例如记录日志,对消息进行编解码
     * 蕾丝reactor模式中的handler类
     */
    private class ChildChannelHandler extends ChannelInitializer<SocketChannel>{
        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
            socketChannel.pipeline().addLast(new TimeServerHandler());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int port = 8088;
        new TimeServer().bind(port);
    }
}
