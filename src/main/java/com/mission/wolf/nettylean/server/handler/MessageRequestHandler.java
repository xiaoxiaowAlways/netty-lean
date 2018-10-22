package com.mission.wolf.nettylean.server.handler;

import com.mission.wolf.nettylean.protocol.PacketCodec;
import com.mission.wolf.nettylean.protocol.request.MessageRequestPacket;
import com.mission.wolf.nettylean.protocol.response.MessageResponsePacket;
import com.mission.wolf.nettylean.session.Session;
import com.mission.wolf.nettylean.util.SessionUtil;

import java.util.Date;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Auther: Will Wang 3
 * @Date: 2018/10/16 14:02
 * @Description:
 */
@ChannelHandler.Sharable
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
  public static final MessageRequestHandler INSTANCE = new MessageRequestHandler();

  private MessageRequestHandler() {
  }
  @Override
  protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket msg) throws Exception {
    Session session = SessionUtil.getSession(ctx.channel());

    MessageResponsePacket response = new MessageResponsePacket();
    response.setFromUserId(session.getUserId());
    response.setFromUserName(session.getUserName());
    response.setMessage(msg.getMessage());

    Channel toUserChannel = SessionUtil.getChannel(msg.getToUserId());

    if (toUserChannel != null && SessionUtil.hasLogin(toUserChannel)) {
      toUserChannel.writeAndFlush(response);
    } else {
      System.err.println("[" + msg.getToUserId() + "] 不在线，发送失败!");
    }
  }
}
