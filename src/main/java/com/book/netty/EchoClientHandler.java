package com.book.netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by xuyifei on 2017/2/24.
 */
public class EchoClientHandler extends ChannelHandlerAdapter {
    private int counter;
    private static final String ECHO_REQ = "Hi Webxle$_";
    public void channelActive(ChannelHandlerContext ctx) {
        for (int i=0;i<10;i++) {
            ctx.writeAndFlush(Unpooled.copiedBuffer(ECHO_REQ.getBytes()));
        }
    }
    public void channelRead(ChannelHandlerContext ctx,Object msg) {
        System.out.println("This is  " +  ++counter + " times receive server :[ " +msg+"] ");
    }

    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.close();
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable e) {
        e.printStackTrace();
    }

}
