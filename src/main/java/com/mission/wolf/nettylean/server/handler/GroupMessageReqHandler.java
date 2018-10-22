package com.mission.wolf.nettylean.server.handler;

import com.mission.wolf.nettylean.protocol.request.GroupMessageReqPacket;
import com.mission.wolf.nettylean.protocol.response.GroupMessageRespPacket;
import com.mission.wolf.nettylean.util.SessionUtil;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @Auther: Will Wang 3
 * @Date: 2018/10/19 15:03
 * @Description:
 */
@ChannelHandler.Sharable
public class GroupMessageReqHandler extends SimpleChannelInboundHandler<GroupMessageReqPacket> {
  public static final GroupMessageReqHandler INSTANCE = new GroupMessageReqHandler();

  private GroupMessageReqHandler() {
  }
  @Override
  protected void channelRead0(ChannelHandlerContext ctx, GroupMessageReqPacket msg) throws Exception {
    String groupId = msg.getToGroupId();

    GroupMessageRespPacket response = new GroupMessageRespPacket();
    response.setFromGroupId(groupId);
    response.setMessage(msg.getMessage());
    response.setFromUser(SessionUtil.getSession(ctx.channel()));

    ChannelGroup group = SessionUtil.getChannelGroup(groupId);
    group.writeAndFlush(response);

  }
}
