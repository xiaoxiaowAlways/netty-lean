package com.mission.wolf.nettylean.server.handler.outbound;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * @Auther: Will Wang 3
 * @Date: 2018/10/16 10:56
 * @Description:
 */
public class OutBoundHandlerB extends ChannelOutboundHandlerAdapter {
  @Override
  public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
    System.out.println("OutBoundHandlerB: " + msg);
//    super.write(ctx, msg + new Date().toString(), promise);
    super.write(ctx,msg,promise);

  }
}
