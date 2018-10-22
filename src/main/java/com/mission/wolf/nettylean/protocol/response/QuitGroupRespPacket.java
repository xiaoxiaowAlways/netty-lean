package com.mission.wolf.nettylean.protocol.response;

import com.mission.wolf.nettylean.protocol.Packet;

import lombok.Data;

import static com.mission.wolf.nettylean.protocol.command.Command.QUIT_GROUP_RESPONSE;

/**
 * @Auther: Will Wang 3
 * @Date: 2018/10/19 12:11
 * @Description:
 */
@Data
public class QuitGroupRespPacket extends Packet {
  private boolean success;
  private String groupId;
  private String reason;

  @Override
  public Byte getCommand() {
    return QUIT_GROUP_RESPONSE;
  }
}
