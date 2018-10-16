package com.mission.wolf.nettylean.server.handler;

import com.mission.wolf.nettylean.protocol.request.LoginRequestPacket;
import com.mission.wolf.nettylean.protocol.response.LoginResponsePacket;
import com.mission.wolf.nettylean.session.Session;
import com.mission.wolf.nettylean.util.SessionUtil;

import java.util.Date;
import java.util.UUID;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Auther: Will Wang 3
 * @Date: 2018/10/16 14:01
 * @Description:
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
  @Override
  protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg) throws Exception {
    System.out.println(new Date() + ": 收到客户端登录请求……");

    LoginResponsePacket request = new LoginResponsePacket();
    request.setVersion(msg.getVersion());
    request.setUserName(msg.getUserName());

    if (valid(msg)) {
      request.setSuccess(true);
      String userId = randomUserId();
      request.setUserId(userId);
      System.out.println("[" + request.getUserName() + ": " + userId + "]登录成功");
      SessionUtil.bindSession(new Session(userId, request.getUserName()), ctx.channel());
    } else {
      request.setReason("账号密码校验失败");
      request.setSuccess(false);
      System.out.println(new Date() + ": 登录失败!");
    }

    ctx.channel().writeAndFlush(request);
  }

  private boolean valid(LoginRequestPacket loginRequestPacket) {
    return true;
  }

  private static String randomUserId() {
    return UUID.randomUUID().toString().split("-")[0];
  }

  public void channelInactive(ChannelHandlerContext ctx) {
    SessionUtil.unBindSession(ctx.channel());
  }
}
