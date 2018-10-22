package com.mission.wolf.nettylean.client.console;

import com.mission.wolf.nettylean.protocol.request.ListGroupMemberReqPacket;

import java.util.Scanner;

import io.netty.channel.Channel;

/**
 * @Auther: Will Wang 3
 * @Date: 2018/10/19 14:11
 * @Description:
 */
public class ListGroupMemberConsoleCommand implements ConsoleCommand {
  @Override
  public void exec(Scanner scanner, Channel channel) {
    System.out.print("输入 groupId，获取成员列表：");
    String groupId = scanner.next();
    ListGroupMemberReqPacket req = new ListGroupMemberReqPacket();

    req.setGroupId(groupId);
    channel.writeAndFlush(req);
  }
}
