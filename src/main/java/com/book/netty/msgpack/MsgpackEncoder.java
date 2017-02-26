package com.book.netty.msgpack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.msgpack.MessagePack;

/**
 * Created by xuyifei on 2017/2/26.
 */
public class MsgpackEncoder extends MessageToByteEncoder<Object>{
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {
        MessagePack messagePack = new MessagePack();
        byte raw[] = messagePack.write(o);
        byteBuf.writeBytes(raw);
    }
}
