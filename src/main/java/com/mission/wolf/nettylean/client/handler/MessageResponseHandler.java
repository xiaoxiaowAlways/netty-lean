package com.mission.wolf.nettylean.client.handler;

import com.mission.wolf.nettylean.protocol.response.MessageResponsePacket;

import java.util.Date;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Auther: Will Wang 3
 * @Date: 2018/10/16 14:14
 * @Description:
 */
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {
  @Override
  protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket msg) throws Exception {
    String fromUserId = msg.getFromUserId();
    String fromUserName = msg.getFromUserName();
    System.out.println(fromUserId + ":" + fromUserName + " -> " + msg.getMessage());
  }
}
