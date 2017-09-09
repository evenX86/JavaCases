package com.book.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.internal.SystemPropertyUtil;

/**
 * Created by xuyifei on 2017/2/14.
 */
public class TimeClientHandler extends ChannelHandlerAdapter{
    private byte[] req;
    private int count;


    public TimeClientHandler() {
         req = ("QUERY TIME ORDER"+ System.getProperty("line.separator")).getBytes();
    }

    /**
     * 当客户端和服务端建立tcp链路成功之后,netty的nio线程就会调用channelActive方法.
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf message = null;
        for (int i=0;i<100;i++) {
            message = Unpooled.buffer(req.length);
            message.writeBytes(req);
            ctx.writeAndFlush(message);//将请求发送给服务端
        }

    }

    /**
     * 当服务器返回应答消息时,channelRead方法被调用
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String) msg;
        System.out.println("Now is : " + body + " ; the counter is : " + ++count);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
