package com.book.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * Created by xuyifei on 2017/2/14.
 */
public class TimeServerHandler extends ChannelHandlerAdapter{

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws UnsupportedEncodingException {
        try {
            ByteBuf buf = (ByteBuf) msg;
            byte[] req = new byte[buf.readableBytes()];
            buf.readBytes(req);
            String body = new String(req,"UTF-8");
            System.out.println("The TimeServer receive order : " + body);
            String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)?new Date(System.currentTimeMillis()).toString():"BAD ORDER";
            ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
            ctx.write(resp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();// 将消息发送队列中的信息写入到serversocket中

    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause) {
        ctx.close();// 发生异常时关闭ChannelHandlerContext
    }
}
