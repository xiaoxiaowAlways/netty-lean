package com.mission.wolf.nettylean.protocol.response;

import com.mission.wolf.nettylean.protocol.Packet;

import lombok.Data;

import static com.mission.wolf.nettylean.protocol.command.Command.LOGIN_RESPONSE;

/**
 * @Auther: Will Wang 3
 * @Date: 2018/10/15 17:49
 * @Description:
 */
@Data
public class LoginResponsePacket extends Packet {
  private String userId;
  private String userName;
  private boolean success;
  private String reason;

  @Override
  public Byte getCommand() {
    return LOGIN_RESPONSE;
  }
}
