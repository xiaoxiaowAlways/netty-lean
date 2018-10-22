package com.mission.wolf.nettylean.client.handler;

import com.mission.wolf.nettylean.protocol.response.LoginResponsePacket;
import com.mission.wolf.nettylean.session.Session;
import com.mission.wolf.nettylean.util.SessionUtil;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Auther: Will Wang 3
 * @Date: 2018/10/16 14:12
 * @Description:
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {
  //  @Override
//  public void channelActive(ChannelHandlerContext ctx) throws Exception {
//    // 创建登录对象
//    LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
//    loginRequestPacket.setUserId(UUID.randomUUID().toString());
//    loginRequestPacket.setUserName("flash");
//    loginRequestPacket.setUserPass("pwd");
//
//    // 写数据
//    ctx.channel().writeAndFlush(loginRequestPacket);
//  }

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket msg) throws Exception {
    String userId = msg.getUserId();
    String userName = msg.getUserName();
    if (msg.isSuccess()) {
      System.out.println("[" + userName + "]登录成功，userId 为: " + msg.getUserId());
      SessionUtil.bindSession(new Session(userId, userName), ctx.channel());
    } else {
      System.out.println("[" + userName + "]登录失败，原因：" + msg.getReason());
    }
  }

  @Override
  public void channelInactive(ChannelHandlerContext ctx) {
    System.out.println("客户端连接被关闭!");
  }
}
