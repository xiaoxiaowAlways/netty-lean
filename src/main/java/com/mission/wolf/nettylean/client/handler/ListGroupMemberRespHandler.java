package com.mission.wolf.nettylean.client.handler;

import com.mission.wolf.nettylean.protocol.response.ListGroupMemberRespPacket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Auther: Will Wang 3
 * @Date: 2018/10/19 14:18
 * @Description:
 */
public class ListGroupMemberRespHandler extends SimpleChannelInboundHandler<ListGroupMemberRespPacket> {


  @Override
  protected void channelRead0(ChannelHandlerContext ctx, ListGroupMemberRespPacket msg) throws Exception {
    System.out.println("群[" + msg.getGroupId() + "]中的人包括：" + msg.getSessions());
  }
}
