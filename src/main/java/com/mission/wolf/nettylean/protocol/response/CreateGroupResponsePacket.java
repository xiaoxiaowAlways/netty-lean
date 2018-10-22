package com.mission.wolf.nettylean.protocol.response;

import com.mission.wolf.nettylean.protocol.Packet;

import java.util.List;

import lombok.Data;

import static com.mission.wolf.nettylean.protocol.command.Command.CREATE_GROUP_RESPONSE;

/**
 * @Auther: Will Wang 3
 * @Date: 2018/10/19 11:31
 * @Description:
 */
@Data
public class CreateGroupResponsePacket extends Packet {
  private boolean success;
  private String groupId;
  private List<String> userNames;

  @Override
  public Byte getCommand() {
    return CREATE_GROUP_RESPONSE;
  }
}
