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

    LoginResponsePacket response = new LoginResponsePacket();
    response.setVersion(msg.getVersion());
    response.setUserName(msg.getUserName());

    if (valid(msg)) {
      response.setSuccess(true);
      String userId = randomUserId();
      response.setUserId(userId);
      System.out.println("[" + response.getUserName() + ": " + userId + "]登录成功");
      SessionUtil.bindSession(new Session(userId, response.getUserName()), ctx.channel());
    } else {
      response.setReason("账号密码校验失败");
      response.setSuccess(false);
      System.out.println(new Date() + ": 登录失败!");
    }

    ctx.channel().writeAndFlush(response);
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
