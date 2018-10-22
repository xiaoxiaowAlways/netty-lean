package com.mission.wolf.nettylean.client.console;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import io.netty.channel.Channel;

/**
 * @Auther: Will Wang 3
 * @Date: 2018/10/19 11:13
 * @Description:
 */
public class ConsoleCommandManager implements ConsoleCommand {
  private Map<String, ConsoleCommand> commandMap;

  public ConsoleCommandManager() {
    commandMap = new HashMap<>();
    commandMap.put("lo", new LogoutConsoleCommand());
    commandMap.put("li", new LoginConsoleCommand());
    commandMap.put("cg", new CreateGroupConsoleCommand());
    commandMap.put("jg", new JoinGroupConsoleCommand());
    commandMap.put("qg", new QuitGroupConsoleCommand());
    commandMap.put("lg", new ListGroupMemberConsoleCommand());
    commandMap.put("stu", new SendToUserConsoleCommand());
    commandMap.put("stg", new SendToGroupConsoleCommand());
  }

  @Override
  public void exec(Scanner scanner, Channel channel) {
    String command = scanner.next();
    ConsoleCommand consoleCommand = commandMap.get(command);
    if (consoleCommand != null) {
      consoleCommand.exec(scanner, channel);
    } else {
      System.err.println("无法识别[" + command + "]指令，请重新输入!");
    }
  }
}
