package com.mission.wolf.nettylean.protocol.request;

import com.mission.wolf.nettylean.protocol.Packet;

import lombok.Data;
import lombok.NoArgsConstructor;

import static com.mission.wolf.nettylean.protocol.command.Command.MESSAGE_REQUEST;

/**
 * @Auther: Will Wang 3
 * @Date: 2018/10/16 10:12
 * @Description:
 */
@Data
public class MessageRequestPacket extends Packet {
  private String toUserId;
  private String message;

  public MessageRequestPacket(String toUserId, String message) {
    this.toUserId = toUserId;
    this.message = message;
  }

  @Override
  public Byte getCommand() {
    return MESSAGE_REQUEST;
  }
}
