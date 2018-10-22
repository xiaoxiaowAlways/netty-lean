package com.mission.wolf.nettylean.client.console;

import com.mission.wolf.nettylean.protocol.request.QuitGroupReqPacket;

import java.util.Scanner;

import io.netty.channel.Channel;

/**
 * @Auther: Will Wang 3
 * @Date: 2018/10/19 12:10
 * @Description:
 */
public class QuitGroupConsoleCommand implements ConsoleCommand {
  @Override
  public void exec(Scanner scanner, Channel channel) {
    System.out.print("输入 groupId，退出群聊：");
    String groupId = scanner.next();

    QuitGroupReqPacket req = new QuitGroupReqPacket();
    req.setGroupId(groupId);

    channel.writeAndFlush(req);
  }
}
