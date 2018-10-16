package com.mission.wolf.nettylean;

import com.mission.wolf.nettylean.protocol.Packet;
import com.mission.wolf.nettylean.protocol.request.LoginRequestPacket;
import com.mission.wolf.nettylean.serializer.impl.JSONSerializer;
import com.mission.wolf.nettylean.protocol.PacketCodec;
import com.mission.wolf.nettylean.serializer.Serializer;

import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * @Auther: Will Wang 3
 * @Date: 2018/10/15 17:08
 * @Description:
 */
public class PacketCodecTest {
  @Test
  public void encode() {

    Serializer serializer = new JSONSerializer();
    LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

    loginRequestPacket.setVersion(((byte) 1));
    loginRequestPacket.setUserId(UUID.randomUUID().toString());
    loginRequestPacket.setUserName("zhangsan");
    loginRequestPacket.setUserPass("password");

    PacketCodec packetcodec = new PacketCodec();
    ByteBuf byteBuf = packetcodec.encode(ByteBufAllocator.DEFAULT,loginRequestPacket);
    Packet decodedPacket = packetcodec.decode(byteBuf);

    System.out.println(packetcodec);
    System.out.println(byteBuf);
    System.out.println(decodedPacket);


    Assert.assertArrayEquals(serializer.serialize(loginRequestPacket), serializer.serialize(decodedPacket));

  }
}
