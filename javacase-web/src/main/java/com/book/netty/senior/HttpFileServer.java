package com.book.netty.senior;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * http服务器
 * Created by xuyifei on 2017/2/28.
 */
public class HttpFileServer {
    private static final String DEFAULT_URL = "/src";
    public void run(final int port,final String url){
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast("http-decoder",new HttpRequestDecoder())//添加http消息解码器
                                    .addLast("http-aggregator",new HttpObjectAggregator(65536)) //将多个消息转换为单个fullhttprequest或者fullhttpresponse
                                    .addLast("http-encoder",new HttpResponseEncoder()) // 对http响应消息进行编码
                                    .addLast("http-chunked",new ChunkedWriteHandler()) // 支持异步发送大的码流
                                    .addLast("fileServerHandler",new HttpFileServerHandler()); //用与文件服务器的业务逻辑处理
                        }
                    });
            ChannelFuture future = b.bind("127.0.0.1",port).sync();
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
