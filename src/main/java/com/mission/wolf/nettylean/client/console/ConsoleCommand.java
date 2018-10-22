package com.mission.wolf.nettylean.client.console;

import java.util.Scanner;

import io.netty.channel.Channel;

/**
 * @Auther: Will Wang 3
 * @Date: 2018/10/19 11:12
 * @Description:
 */
public interface ConsoleCommand {
  void exec(Scanner scanner, Channel channel);
}
