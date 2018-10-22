package com.mission.wolf.nettylean.server.handler;

import com.mission.wolf.nettylean.protocol.request.JoinGroupReqPacket;
import com.mission.wolf.nettylean.protocol.response.JoinGroupRespPacket;
import com.mission.wolf.nettylean.util.SessionUtil;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @Auther: Will Wang 3
 * @Date: 2018/10/19 12:05
 * @Description:
 */
@ChannelHandler.Sharable
public class JoinGroupReqHandler extends SimpleChannelInboundHandler<JoinGroupReqPacket> {
  public static final JoinGroupReqHandler INSTANCE = new JoinGroupReqHandler();

  private JoinGroupReqHandler() {
  }
  @Override
  protected void channelRead0(ChannelHandlerContext ctx, JoinGroupReqPacket msg) throws Exception {
    // 1. 获取群对应的 channelGroup，然后将当前用户的 channel 添加进去
    String groupId = msg.getGroupId();
    ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
    channelGroup.add(ctx.channel());

    JoinGroupRespPacket resp = new JoinGroupRespPacket();
    resp.setSuccess(true);
    resp.setGroupId(groupId);
    ctx.writeAndFlush(resp);

  }
}
