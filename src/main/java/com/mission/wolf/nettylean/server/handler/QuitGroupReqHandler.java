package com.mission.wolf.nettylean.server.handler;

import com.mission.wolf.nettylean.protocol.request.QuitGroupReqPacket;
import com.mission.wolf.nettylean.protocol.response.QuitGroupRespPacket;
import com.mission.wolf.nettylean.util.SessionUtil;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @Auther: Will Wang 3
 * @Date: 2018/10/19 12:16
 * @Description:
 */
@ChannelHandler.Sharable
public class QuitGroupReqHandler extends SimpleChannelInboundHandler<QuitGroupReqPacket> {
  public static final QuitGroupReqHandler INSTANCE = new QuitGroupReqHandler();

  private QuitGroupReqHandler() {
  }

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, QuitGroupReqPacket msg) throws Exception {
    // 1. 获取群对应的 channelGroup，然后将当前用户的 channel 移除
    String groupId = msg.getGroupId();
    ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
    channelGroup.remove(ctx.channel());

    // 2. 构造退群响应发送给客户端
    QuitGroupRespPacket response = new QuitGroupRespPacket();

    response.setGroupId(msg.getGroupId());
    response.setSuccess(true);
    ctx.writeAndFlush(response);
  }
}
