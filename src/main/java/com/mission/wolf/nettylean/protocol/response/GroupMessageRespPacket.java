package com.mission.wolf.nettylean.protocol.response;

import com.mission.wolf.nettylean.protocol.Packet;
import com.mission.wolf.nettylean.session.Session;

import lombok.Data;

import static com.mission.wolf.nettylean.protocol.command.Command.GROUP_MESSAGE_RESPONSE;

/**
 * @Auther: Will Wang 3
 * @Date: 2018/10/19 14:58
 * @Description:
 */
@Data
public class GroupMessageRespPacket extends Packet {
  private String fromGroupId;
  private String message;
  private Session fromUser;

  @Override
  public Byte getCommand() {
    return GROUP_MESSAGE_RESPONSE;
  }
}
