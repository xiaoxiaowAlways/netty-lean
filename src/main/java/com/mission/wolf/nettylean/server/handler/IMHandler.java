package com.mission.wolf.nettylean.server.handler;

import com.mission.wolf.nettylean.client.handler.CreateGroupResponseHandler;
import com.mission.wolf.nettylean.protocol.Packet;
import com.mission.wolf.nettylean.protocol.response.QuitGroupRespPacket;

import java.util.HashMap;
import java.util.Map;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import static com.mission.wolf.nettylean.protocol.command.Command.*;

/**
 * @Auther: Will Wang 3
 * @Date: 2018/10/19 15:21
 * @Description:
 */
@ChannelHandler.Sharable
public class IMHandler extends SimpleChannelInboundHandler<Packet> {
  public static final IMHandler INSTANCE = new IMHandler();
  private Map<Byte, SimpleChannelInboundHandler<? extends Packet>> handlerMap;

  private IMHandler() {
    handlerMap = new HashMap<>();

    handlerMap.put(MESSAGE_REQUEST, MessageRequestHandler.INSTANCE);
    handlerMap.put(CREATE_GROUP_REQUEST, CreateGroupRequestHandler.INSTANCE);
    handlerMap.put(JOIN_GROUP_REQUEST, JoinGroupReqHandler.INSTANCE);
    handlerMap.put(QUIT_GROUP_REQUEST, QuitGroupReqHandler.INSTANCE);
    handlerMap.put(LIST_GROUP_MEMBER_REQUEST, ListGroupMemberReqHandler.INSTANCE);
    handlerMap.put(GROUP_MESSAGE_REQUEST, GroupMessageReqHandler.INSTANCE);
//    handlerMap.put(LOGOUT_REQUEST, Lout.INSTANCE);

  }

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, Packet msg) throws Exception {
    handlerMap.get(msg.getCommand()).channelRead(ctx, msg);
  }
}
