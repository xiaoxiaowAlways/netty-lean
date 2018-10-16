package com.mission.wolf.nettylean.server.handler.inbound;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Auther: Will Wang 3
 * @Date: 2018/10/16 10:55
 * @Description:
 */
public class InBoundHandlerA extends ChannelInboundHandlerAdapter {
  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    System.out.println("InBoundHandlerA: " + msg);
//    super.channelRead(ctx, msg + new Date().toString());
    super.channelRead(ctx, msg);
    //略过所有的inBound而直接传递到第一个OutBoundHandler
    //ctx.channel().writeAndFlush(msg);
  }
}
