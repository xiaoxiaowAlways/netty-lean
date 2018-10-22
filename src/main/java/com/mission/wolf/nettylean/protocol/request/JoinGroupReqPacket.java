package com.mission.wolf.nettylean.protocol.request;

import com.mission.wolf.nettylean.protocol.Packet;

import lombok.Data;

import static com.mission.wolf.nettylean.protocol.command.Command.JOIN_GROUP_REQUEST;

/**
 * @Auther: Will Wang 3
 * @Date: 2018/10/19 12:01
 * @Description:
 */
@Data
public class JoinGroupReqPacket extends Packet {
  private String groupId;

  @Override
  public Byte getCommand() {
    return JOIN_GROUP_REQUEST;
  }
}
