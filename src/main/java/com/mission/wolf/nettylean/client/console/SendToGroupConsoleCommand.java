package com.mission.wolf.nettylean.client.console;

import com.mission.wolf.nettylean.protocol.request.GroupMessageReqPacket;

import java.util.Scanner;

import io.netty.channel.Channel;

/**
 * @Auther: Will Wang 3
 * @Date: 2018/10/19 15:43
 * @Description:
 */
public class SendToGroupConsoleCommand implements ConsoleCommand {
  @Override
  public void exec(Scanner scanner, Channel channel) {
    System.out.print("发送消息给某个某个群组：");

    String toGroupId = scanner.next();
    String message = scanner.next();
    GroupMessageReqPacket request = new GroupMessageReqPacket(toGroupId, message);
    channel.writeAndFlush(request);

  }
}
