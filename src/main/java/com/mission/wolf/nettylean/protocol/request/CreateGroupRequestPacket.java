package com.mission.wolf.nettylean.protocol.request;

import com.mission.wolf.nettylean.protocol.Packet;

import java.util.List;

import lombok.Data;

import static com.mission.wolf.nettylean.protocol.command.Command.CREATE_GROUP_REQUEST;

/**
 * @Auther: Will Wang 3
 * @Date: 2018/10/19 11:18
 * @Description:
 */
@Data
public class CreateGroupRequestPacket extends Packet {
  private List<String> userIds;

  @Override
  public Byte getCommand() {
    return CREATE_GROUP_REQUEST;
  }
}
