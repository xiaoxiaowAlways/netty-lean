package com.mission.wolf.nettylean.protocol.request;

import com.mission.wolf.nettylean.protocol.Packet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.mission.wolf.nettylean.protocol.command.Command.GROUP_MESSAGE_REQUEST;

/**
 * @Auther: Will Wang 3
 * @Date: 2018/10/19 14:56
 * @Description:
 */
@Data
public class GroupMessageReqPacket extends Packet {
  private String toGroupId;
  private String message;

  public GroupMessageReqPacket() {
  }

  public GroupMessageReqPacket(String toGroupId, String message) {
    this.toGroupId = toGroupId;
    this.message = message;
  }

  @Override
  public Byte getCommand() {
    return GROUP_MESSAGE_REQUEST;
  }
}
