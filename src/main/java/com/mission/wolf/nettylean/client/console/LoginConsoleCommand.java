package com.mission.wolf.nettylean.client.console;

import com.mission.wolf.nettylean.protocol.request.LoginRequestPacket;

import java.util.Scanner;

import io.netty.channel.Channel;

/**
 * @Auther: Will Wang 3
 * @Date: 2018/10/19 11:16
 * @Description:
 */
public class LoginConsoleCommand implements ConsoleCommand {
  @Override
  public void exec(Scanner scanner, Channel channel) {
    LoginRequestPacket request = new LoginRequestPacket();

    System.out.print("输入用户名登录: ");
    request.setUserName(scanner.nextLine());
    request.setUserPass("pwd");

    // 发送登录数据包
    channel.writeAndFlush(request);
    waitForLoginResponse();
  }

  private static void waitForLoginResponse() {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException ignored) {
    }
  }

}
