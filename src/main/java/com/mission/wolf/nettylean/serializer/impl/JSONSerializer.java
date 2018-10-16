package com.mission.wolf.nettylean.serializer.impl;

import com.alibaba.fastjson.JSON;
import com.mission.wolf.nettylean.serializer.Serializer;
import com.mission.wolf.nettylean.serializer.SerializerAlgorithm;

/**
 * @Auther: Will Wang 3
 * @Date: 2018/10/15 16:41
 * @Description:
 */
public class JSONSerializer implements Serializer {
  @Override
  public byte getSerializerAlgorithm() {
    return SerializerAlgorithm.JSON;
  }

  @Override
  public byte[] serialize(Object object) {
    return JSON.toJSONBytes(object);
  }

  @Override
  public <T> T deserialize(Class<T> clazz, byte[] bytes) {
    return JSON.parseObject(bytes, clazz);
  }
}
