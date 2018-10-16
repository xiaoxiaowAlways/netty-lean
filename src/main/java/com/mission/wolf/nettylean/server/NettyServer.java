package com.mission.wolf.nettylean.server;

import com.mission.wolf.nettylean.codec.PacketDecoder;
import com.mission.wolf.nettylean.codec.PacketEncoder;
import com.mission.wolf.nettylean.codec.Spliter;
import com.mission.wolf.nettylean.server.handler.AuthHandler;
import com.mission.wolf.nettylean.server.handler.LifeCyCleTestHandler;
import com.mission.wolf.nettylean.server.handler.LoginRequestHandler;
import com.mission.wolf.nettylean.server.handler.MessageRequestHandler;

import java.util.Date;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @Auther: Will Wang 3
 * @Date: 2018/10/15 17:33
 * @Description:
 */
public class NettyServer {
  private static final int PORT = 8000;

  public static void main(String[] args) {
    NioEventLoopGroup bossGroup = new NioEventLoopGroup();
    NioEventLoopGroup workerGroup = new NioEventLoopGroup();

    final ServerBootstrap serverBootstrap = new ServerBootstrap();
    serverBootstrap
        .group(bossGroup, workerGroup)
        .channel(NioServerSocketChannel.class)
        .option(ChannelOption.SO_BACKLOG, 1024)
        .childOption(ChannelOption.SO_KEEPALIVE, true)
        .childOption(ChannelOption.TCP_NODELAY, true)
        .childHandler(new ChannelInitializer<NioSocketChannel>() {
          protected void initChannel(NioSocketChannel ch) {
//            ch.pipeline().addLast(new LifeCyCleTestHandler());
            ch.pipeline().addLast(new Spliter());
            ch.pipeline().addLast(new PacketDecoder());
            ch.pipeline().addLast(new LoginRequestHandler());
            ch.pipeline().addLast(new AuthHandler());
            ch.pipeline().addLast(new MessageRequestHandler());
            ch.pipeline().addLast(new PacketEncoder());

          }
        });
    bind(serverBootstrap, PORT);
  }

  private static void bind(final ServerBootstrap serverBootstrap, final int port) {
    serverBootstrap.bind(port).addListener(future -> {
      if (future.isSuccess()) {
        System.out.println(new Date() + ": 端口[" + port + "]绑定成功!");
      } else {
        System.err.println("端口[" + port + "]绑定失败!");
      }
    });
  }
}
