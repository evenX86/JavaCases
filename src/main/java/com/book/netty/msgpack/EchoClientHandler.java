package com.book.netty.msgpack;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by xuyifei on 2017/2/26.
 */
public class EchoClientHandler extends ChannelHandlerAdapter{
    private final int sendNumber;

    public EchoClientHandler(int sendNumber) {
        this.sendNumber = sendNumber;
    }
    public void channelActive(ChannelHandlerContext ctx) {
        UserInfo userInfos =  new UserInfo("k",1);
    }
}
