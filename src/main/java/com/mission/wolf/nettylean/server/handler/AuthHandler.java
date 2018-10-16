package com.mission.wolf.nettylean.server.handler;

import com.mission.wolf.nettylean.util.SessionUtil;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Auther: Will Wang 3
 * @Date: 2018/10/16 17:27
 * @Description:
 */
public class AuthHandler extends ChannelInboundHandlerAdapter {

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    if (!SessionUtil.hasLogin(ctx.channel())) {
      ctx.channel().close();
    } else {
      ctx.pipeline().remove(this);
      super.channelRead(ctx, msg);
    }
  }


  @Override
  public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
    if (SessionUtil.hasLogin(ctx.channel())) {
      System.out.println("当前连接登录验证完毕，无需再次验证, AuthHandler 被移除");
    } else {
      System.out.println("无登录验证，强制关闭连接!");
    }
  }
}
