package com.f4.linkage.webserver.api.chat.controller.event;

import com.f4.linkage.webserver.api.chat.service.OnlineUserHub;
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
  @Autowired
  OnlineUserHub onlineUserHub;
  @Override
  public void onApplicationEvent(SessionDisconnectEvent sessionDisconnectEvent) {
    System.out.println(Objects.requireNonNull(sessionDisconnectEvent.getUser()).getName()+" is leaving!");
    onlineUserHub.userDisconnect(sessionDisconnectEvent.getUser().getName());
  }
}
