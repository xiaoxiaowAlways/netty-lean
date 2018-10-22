package com.mission.wolf.nettylean.client.handler;

import com.mission.wolf.nettylean.protocol.response.GroupMessageRespPacket;
import com.mission.wolf.nettylean.session.Session;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Auther: Will Wang 3
 * @Date: 2018/10/19 15:49
 * @Description:
 */
public class GroupMessageRespHandler extends SimpleChannelInboundHandler<GroupMessageRespPacket> {
  @Override
  protected void channelRead0(ChannelHandlerContext ctx, GroupMessageRespPacket msg) throws Exception {
    String fromGroupId = msg.getFromGroupId();
    Session fromUser = msg.getFromUser();
    System.out.println("收到群[" + fromGroupId + "]中[" + fromUser + "]发来的消息：" + msg.getMessage());
  }
}
