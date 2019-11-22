package com.f4.linkage.webserver.api.chat.controller.event;

import com.f4.linkage.webserver.config.OnlineUserHub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Objects;

/**
 * @program: Linkage
 * @description: To capture user disconnection.
 * @author: Zijian Zhang
 * @create: 2019/10/28
 **/
public class StompDisconnectEvent implements ApplicationListener<SessionDisconnectEvent> {
  private Logger logger = LoggerFactory.getLogger(StompDisconnectEvent.class);

  @Autowired
  OnlineUserHub onlineUserHub;
  @Override
  public void onApplicationEvent(SessionDisconnectEvent sessionDisconnectEvent) {
    logger.info(Objects.requireNonNull(sessionDisconnectEvent.getUser()).getName()+" is leaving!");
    System.out.println(Objects.requireNonNull(sessionDisconnectEvent.getUser()).getName()+" is leaving!");
    onlineUserHub.userDisconnect(Objects.requireNonNull(sessionDisconnectEvent.getUser()).getName());
  }
}
