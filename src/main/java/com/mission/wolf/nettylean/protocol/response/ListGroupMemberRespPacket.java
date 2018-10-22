package com.mission.wolf.nettylean.protocol.response;

import com.mission.wolf.nettylean.protocol.Packet;
import com.mission.wolf.nettylean.session.Session;

import java.util.List;

import lombok.Data;

import static com.mission.wolf.nettylean.protocol.command.Command.LIST_GROUP_MEMBER_RESPONSE;

/**
 * @Auther: Will Wang 3
 * @Date: 2018/10/19 14:12
 * @Description:
 */
@Data
public class ListGroupMemberRespPacket extends Packet {
  private String groupId;
  private List<Session> sessions;

  @Override
  public Byte getCommand() {
    return LIST_GROUP_MEMBER_RESPONSE;
  }
}
