package com.mission.wolf.nettylean.client.handler;

import com.mission.wolf.nettylean.protocol.response.QuitGroupRespPacket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Auther: Will Wang 3
 * @Date: 2018/10/19 14:03
 * @Description:
 */
public class QuitGroupResponseHandler extends SimpleChannelInboundHandler<QuitGroupRespPacket> {
  @Override
  protected void channelRead0(ChannelHandlerContext ctx, QuitGroupRespPacket msg) throws Exception {
    if (msg.isSuccess()) {
      System.out.println("退出群[" + msg.getGroupId() + "]成功!");
    } else {
      System.err.println("退出群[" + msg.getGroupId() + "]失败，原因为：" + msg.getReason());
    }
  }
}
