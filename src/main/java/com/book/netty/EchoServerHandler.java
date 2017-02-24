package com.book.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by xuyifei on 2017/2/24.
 */
public class EchoServerHandler extends ChannelHandlerAdapter {
    int counter = 0;
    public void channelRead(ChannelHandlerContext ctx,Object message) {
        String body = (String) message;
        System.out.println("This is " + ++counter + " times receive client: " +body);
        body += "$_";
        ByteBuf echo = Unpooled.copiedBuffer(body.getBytes());
        ctx.writeAndFlush(echo);
    }
    public void exceptionCaught(ChannelHandlerContext ctx,Throwable e) {
        e.printStackTrace();
    }

}
