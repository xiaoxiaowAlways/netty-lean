package com.mission.wolf.nettylean.codec;

import com.mission.wolf.nettylean.protocol.Packet;
import com.mission.wolf.nettylean.protocol.PacketCodec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @Auther: Will Wang 3
 * @Date: 2018/10/16 13:56
 * @Description:
 */
public class PacketEncoder extends MessageToByteEncoder<Packet> {
  @Override
  protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) throws Exception {
    PacketCodec.INSTANCE.encode(out, msg);
  }
}
