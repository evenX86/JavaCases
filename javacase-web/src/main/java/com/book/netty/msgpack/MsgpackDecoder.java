package com.book.netty.msgpack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.msgpack.MessagePack;

import java.util.List;

/**
 * Created by xuyifei on 2017/2/26.
 */
public class MsgpackDecoder extends MessageToMessageDecoder<ByteBuf>{
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf o, List<Object> list) throws Exception {
        final byte[] array;
        final int length = o.readableBytes();
        array = new byte[length];
        o.getBytes(o.readerIndex(),array, 0, length);
        MessagePack msgpack = new MessagePack();
        list.add(msgpack);
    }
}
