package com.mission.wolf.nettylean.serializer;

import com.mission.wolf.nettylean.serializer.impl.JSONSerializer;

/**
 * @Auther: Will Wang 3
 * @Date: 2018/10/15 16:36
 * @Description:
 */
public interface Serializer {
  byte JSON_SERIALIZER = 1;
  Serializer DEFAULT = new JSONSerializer();

  /**
   * 序列化算法
   */
  byte getSerializerAlgorithm();

  /**
   * java 对象转二进制
   */
  byte[] serialize(Object object);

  /**
   * 二进制转 java 对象
   */
  <T> T deserialize(Class<T> clazz, byte[] bytes);
}
