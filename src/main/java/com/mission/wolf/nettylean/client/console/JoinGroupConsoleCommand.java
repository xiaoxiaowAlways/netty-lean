package com.mission.wolf.nettylean.client.console;

import com.mission.wolf.nettylean.protocol.request.JoinGroupReqPacket;

import java.util.Scanner;

import io.netty.channel.Channel;

/**
 * @Auther: Will Wang 3
 * @Date: 2018/10/19 12:00
 * @Description:
 */
public class JoinGroupConsoleCommand implements ConsoleCommand {
  @Override
  public void exec(Scanner scanner, Channel channel) {
    JoinGroupReqPacket req = new JoinGroupReqPacket();

    System.out.print("输入 groupId，加入群聊：");
    String groupId = scanner.next();

    req.setGroupId(groupId);
    channel.writeAndFlush(req);
  }
}
