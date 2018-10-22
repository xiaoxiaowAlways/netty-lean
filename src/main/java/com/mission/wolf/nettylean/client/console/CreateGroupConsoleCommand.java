package com.mission.wolf.nettylean.client.console;

import com.mission.wolf.nettylean.protocol.request.CreateGroupRequestPacket;

import java.util.Arrays;
import java.util.Scanner;

import io.netty.channel.Channel;

/**
 * @Auther: Will Wang 3
 * @Date: 2018/10/19 11:16
 * @Description:
 */
public class CreateGroupConsoleCommand implements ConsoleCommand {
  private static final String USER_ID_SPLITER = ",";

  @Override
  public void exec(Scanner scanner, Channel channel) {
    System.out.println("【拉人群聊】输入 userId 列表，userId 之间英文逗号隔开：");
    String userIds = scanner.next();

    CreateGroupRequestPacket packet = new CreateGroupRequestPacket();
    packet.setUserIds(Arrays.asList(userIds.split(USER_ID_SPLITER)));
    channel.writeAndFlush(packet);
  }
}
