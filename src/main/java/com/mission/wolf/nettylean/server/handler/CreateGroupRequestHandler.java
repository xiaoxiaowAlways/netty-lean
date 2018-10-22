package com.mission.wolf.nettylean.server.handler;

import com.mission.wolf.nettylean.protocol.request.CreateGroupRequestPacket;
import com.mission.wolf.nettylean.protocol.response.CreateGroupResponsePacket;
import com.mission.wolf.nettylean.util.IDUtil;
import com.mission.wolf.nettylean.util.SessionUtil;

import java.util.ArrayList;
import java.util.List;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;

/**
 * @Auther: Will Wang 3
 * @Date: 2018/10/19 11:28
 * @Description:
 */
@ChannelHandler.Sharable
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {

  public static final CreateGroupRequestHandler INSTANCE = new CreateGroupRequestHandler();

  private CreateGroupRequestHandler() {
  }
  @Override
  protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket msg) throws Exception {
    List<String> userIds = msg.getUserIds();

    List<String> userName = new ArrayList<>();

    //1. 创建分组
    ChannelGroup group = new DefaultChannelGroup(ctx.executor());

    //2. 筛选出待加入群聊的用户 channel 和 userName
    for (String userId : userIds) {
      Channel channel = SessionUtil.getChannel(userId);
      if (channel != null) {
        group.add(channel);
        userName.add(SessionUtil.getSession(channel).getUserName());
      }
    }

    //3. 创建群聊创建结果的响应
    String groupId = IDUtil.randomId();
    CreateGroupResponsePacket response = new CreateGroupResponsePacket();
    response.setSuccess(true);
    response.setGroupId(groupId);
    response.setUserNames(userName);

    //4.给每个客户发送拉群通知
    group.writeAndFlush(response);
    System.out.print("群创建成功，id 为[" + response.getGroupId() + "], ");
    System.out.println("群里面有：" + response.getUserNames());
    // 5. 保存群组相关的信息
    SessionUtil.bindChannelGroup(groupId, group);

  }
}
