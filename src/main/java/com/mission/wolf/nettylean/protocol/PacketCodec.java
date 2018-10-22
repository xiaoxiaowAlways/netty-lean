package com.mission.wolf.nettylean.protocol;

import com.apple.eawt.QuitResponse;
import com.mission.wolf.nettylean.protocol.request.CreateGroupRequestPacket;
import com.mission.wolf.nettylean.protocol.request.GroupMessageReqPacket;
import com.mission.wolf.nettylean.protocol.request.JoinGroupReqPacket;
import com.mission.wolf.nettylean.protocol.request.ListGroupMemberReqPacket;
import com.mission.wolf.nettylean.protocol.request.MessageRequestPacket;
import com.mission.wolf.nettylean.protocol.request.QuitGroupReqPacket;
import com.mission.wolf.nettylean.protocol.response.CreateGroupResponsePacket;
import com.mission.wolf.nettylean.protocol.response.GroupMessageRespPacket;
import com.mission.wolf.nettylean.protocol.response.JoinGroupRespPacket;
import com.mission.wolf.nettylean.protocol.response.ListGroupMemberRespPacket;
import com.mission.wolf.nettylean.protocol.response.LoginResponsePacket;
import com.mission.wolf.nettylean.protocol.response.MessageResponsePacket;
import com.mission.wolf.nettylean.protocol.response.QuitGroupRespPacket;
import com.mission.wolf.nettylean.serializer.impl.JSONSerializer;
import com.mission.wolf.nettylean.serializer.Serializer;
import com.mission.wolf.nettylean.protocol.request.LoginRequestPacket;

import java.util.HashMap;
import java.util.Map;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import static com.mission.wolf.nettylean.protocol.command.Command.*;

/**
 * @Auther: Will Wang 3
 * @Date: 2018/10/15 16:44
 * @Description:
 */
public class PacketCodec {


  public static final PacketCodec INSTANCE = new PacketCodec();
  public static final int MAGIC_NUMBER = 0x12345678;
  private static final Map<Byte, Class<? extends Packet>> packetTypeMap;
  private static final Map<Byte, Serializer> serializerMap;

  static {
    packetTypeMap = new HashMap<>();
    packetTypeMap.put(LOGIN_REQUEST, LoginRequestPacket.class);
    packetTypeMap.put(LOGIN_RESPONSE, LoginResponsePacket.class);
    packetTypeMap.put(MESSAGE_REQUEST, MessageRequestPacket.class);
    packetTypeMap.put(MESSAGE_RESPONSE, MessageResponsePacket.class);
    packetTypeMap.put(CREATE_GROUP_REQUEST, CreateGroupRequestPacket.class);
    packetTypeMap.put(CREATE_GROUP_RESPONSE, CreateGroupResponsePacket.class);
    packetTypeMap.put(JOIN_GROUP_REQUEST, JoinGroupReqPacket.class);
    packetTypeMap.put(JOIN_GROUP_RESPONSE, JoinGroupRespPacket.class);
    packetTypeMap.put(QUIT_GROUP_REQUEST, QuitGroupReqPacket.class);
    packetTypeMap.put(QUIT_GROUP_RESPONSE, QuitGroupRespPacket.class);
    packetTypeMap.put(LIST_GROUP_MEMBER_REQUEST, ListGroupMemberReqPacket.class);
    packetTypeMap.put(LIST_GROUP_MEMBER_RESPONSE, ListGroupMemberRespPacket.class);
    packetTypeMap.put(GROUP_MESSAGE_REQUEST, GroupMessageReqPacket.class);
    packetTypeMap.put(GROUP_MESSAGE_RESPONSE, GroupMessageRespPacket.class);
//    packetTypeMap.put(LOGOUT_REQUEST, Logo.class);
//    packetTypeMap.put(LOGOUT_RESPONSE, ListGroupMemberReqPacket.class);


    serializerMap = new HashMap<>();
    Serializer serializer = new JSONSerializer();
    serializerMap.put(serializer.getSerializerAlgorithm(), serializer);
  }


  public ByteBuf encode(ByteBufAllocator allocator, Packet packet) {
    //1. 创建 ByteBuf 对象
    ByteBuf byteBuf = allocator.DEFAULT.ioBuffer();

    return encode(byteBuf, packet);
  }

  public ByteBuf encode(ByteBuf byteBuf, Packet packet) {
    //2. 序列化 Java 对象
    byte[] bytes = Serializer.DEFAULT.serialize(packet);
    //3. 编码
    byteBuf.writeInt(MAGIC_NUMBER);
    byteBuf.writeByte(packet.getVersion());
    byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
    byteBuf.writeByte(packet.getCommand());
    byteBuf.writeInt(bytes.length);
    byteBuf.writeBytes(bytes);

    return byteBuf;
  }

  public Packet decode(ByteBuf byteBuf) {
    //跳过魔数
    byteBuf.skipBytes(4);

    //跳过版本号
    byteBuf.skipBytes(1);

    // 序列化算法标识
    byte serializerAlgorithm = byteBuf.readByte();

    //指令
    byte command = byteBuf.readByte();

    //数据包长度
    int length = byteBuf.readInt();

    byte[] bytes = new byte[length];
    byteBuf.readBytes(bytes);

    Class<? extends Packet> requestType = getRequestType(command);
    Serializer serializer = getSerializer(serializerAlgorithm);

    if (requestType != null && serializer != null) {
      return serializer.deserialize(requestType, bytes);
    } else {
      System.err.println("找不对对应的 packetType,command: " + command);
      return null;
    }

  }

  private Serializer getSerializer(byte serializeAlgorithm) {

    return serializerMap.get(serializeAlgorithm);
  }

  private Class<? extends Packet> getRequestType(byte command) {

    return packetTypeMap.get(command);
  }
}
