package com.mission.wolf.nettylean.server.handler;

import com.mission.wolf.nettylean.protocol.request.ListGroupMemberReqPacket;
import com.mission.wolf.nettylean.protocol.response.ListGroupMemberRespPacket;
import com.mission.wolf.nettylean.session.Session;
import com.mission.wolf.nettylean.util.SessionUtil;

import java.util.ArrayList;
import java.util.List;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @Auther: Will Wang 3
 * @Date: 2018/10/19 14:13
 * @Description:
 */
@ChannelHandler.Sharable
public class ListGroupMemberReqHandler extends SimpleChannelInboundHandler<ListGroupMemberReqPacket> {
  public static final ListGroupMemberReqHandler INSTANCE = new ListGroupMemberReqHandler();

  private ListGroupMemberReqHandler() {
  }
  @Override
  protected void channelRead0(ChannelHandlerContext ctx, ListGroupMemberReqPacket msg) throws Exception {
    String groupId = msg.getGroupId();
    ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);

    List<Session> list = new ArrayList<>();

    for (Channel channel : channelGroup) {
      Session session = SessionUtil.getSession(channel);
      list.add(session);
    }

    ListGroupMemberRespPacket response = new ListGroupMemberRespPacket();
    response.setGroupId(groupId);
    response.setSessions(list);

    ctx.writeAndFlush(response);

  }
}
