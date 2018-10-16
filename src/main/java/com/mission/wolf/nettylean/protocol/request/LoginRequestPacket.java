package com.mission.wolf.nettylean.protocol.request;

import com.mission.wolf.nettylean.protocol.Packet;

import lombok.Data;

import static com.mission.wolf.nettylean.protocol.command.Command.LOGIN_REQUEST;


/**
 * @Auther: Will Wang 3
 * @Date: 2018/10/15 16:34
 * @Description:
 */
@Data
public class LoginRequestPacket extends Packet {

  private String userId;
  private String userName;
  private String userPass;

  @Override
  public Byte getCommand() {
    return LOGIN_REQUEST;
  }


}
