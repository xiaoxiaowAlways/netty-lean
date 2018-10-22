package com.mission.wolf.nettylean.client.handler;

import com.mission.wolf.nettylean.protocol.response.JoinGroupRespPacket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Auther: Will Wang 3
 * @Date: 2018/10/19 12:09
 * @Description:
 */
public class JoinGroupRespHandler extends SimpleChannelInboundHandler<JoinGroupRespPacket> {




  @Override
  protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRespPacket msg) throws Exception {
    if (msg.isSuccess()) {
      System.out.println("加入群[" + msg.getGroupId() + "]成功!");
    } else {
      System.err.println("加入群[" + msg.getGroupId() + "]失败，原因为：" + msg.getReason());
    }
  }
}
