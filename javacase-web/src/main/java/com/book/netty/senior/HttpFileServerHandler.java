package com.book.netty.senior;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * Created by xuyifei on 2017/2/28.
 */
public class HttpFileServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, FullHttpRequest fullHttpRequest) throws Exception {

    }
}
