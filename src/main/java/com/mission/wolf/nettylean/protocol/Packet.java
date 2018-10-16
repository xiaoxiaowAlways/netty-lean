package com.mission.wolf.nettylean.protocol;

import lombok.Data;

/**
 * @Auther: Will Wang 3
 * @Date: 2018/10/15 16:31
 * @Description:
 */
@Data
public abstract class Packet {

  /**
   * packet 数据结构
   * 魔数 | 版本号 | 序列化算法 | 指令 | 数据长度 | 数据
   * 4byte  1byte    1byte     1byte  4byte     N byte
   */

  /**
   * 版本号
   */
  private Byte version = 1;

  /**
   * 指令
   */
  public abstract Byte getCommand();
}
