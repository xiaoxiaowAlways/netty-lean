package com.mission.wolf.nettylean.codec;

import com.mission.wolf.nettylean.protocol.PacketCodec;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * @Auther: Will Wang 3
 * @Date: 2018/10/16 13:56
 * @Description:
 */
public class PacketDecoder extends ByteToMessageDecoder {
  @Override
  protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
    out.add(PacketCodec.INSTANCE.decode(in));
  }
}
