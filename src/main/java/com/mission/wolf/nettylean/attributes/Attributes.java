package com.mission.wolf.nettylean.attributes;

import com.mission.wolf.nettylean.session.Session;

import io.netty.util.AttributeKey;

/**
 * @Auther: Will Wang 3
 * @Date: 2018/10/16 10:17
 * @Description:
 */
public interface Attributes {

  AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
  AttributeKey<Session> SESSION = AttributeKey.newInstance("session");

}
