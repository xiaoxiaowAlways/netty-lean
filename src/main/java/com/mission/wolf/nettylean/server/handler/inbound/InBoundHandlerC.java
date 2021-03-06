package com.mission.wolf.nettylean.server.handler.inbound;

import java.util.Date;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Auther: Will Wang 3
 * @Date: 2018/10/16 10:55
 * @Description:
 */
public class InBoundHandlerC extends ChannelInboundHandlerAdapter {
  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    System.out.println("InBoundHandlerC: " + msg);
//    super.channelRead(ctx, msg + new Date().toString());
//    super.channelRead(ctx, msg);
    ctx.channel().writeAndFlush(msg+ new Date().toString());
  }
}
