package com.mission.wolf.nettylean.protocol.response;

import com.mission.wolf.nettylean.protocol.Packet;

import lombok.Data;

import static com.mission.wolf.nettylean.protocol.command.Command.MESSAGE_RESPONSE;

/**
 * @Auther: Will Wang 3
 * @Date: 2018/10/16 10:12
 * @Description:
 */
@Data
public class MessageResponsePacket extends Packet {
  private String fromUserId;
  private String fromUserName;
  private String message;

  @Override
  public Byte getCommand() {
    return MESSAGE_RESPONSE;
  }
}
