package com.mission.wolf.nettylean.protocol.request;

import com.mission.wolf.nettylean.protocol.Packet;

import lombok.Data;

import static com.mission.wolf.nettylean.protocol.command.Command.LIST_GROUP_MEMBER_REQUEST;

/**
 * @Auther: Will Wang 3
 * @Date: 2018/10/19 14:12
 * @Description:
 */
@Data
public class ListGroupMemberReqPacket extends Packet {
  private String groupId;

  @Override
  public Byte getCommand() {
    return LIST_GROUP_MEMBER_REQUEST;
  }
}
