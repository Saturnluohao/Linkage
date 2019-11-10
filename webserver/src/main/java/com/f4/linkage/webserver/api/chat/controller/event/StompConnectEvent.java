package com.f4.linkage.webserver.api.chat.controller.event;

import com.f4.linkage.webserver.config.OnlineUserHub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

import java.util.Objects;

/**
 * @program: Linkage
 * @description: To capture user connection.
 * @author: Zijian Zhang
 * @create: 2019/10/28
 **/

public class StompConnectEvent implements ApplicationListener<SessionConnectedEvent> {
  @Autowired
  OnlineUserHub onlineUserHub;

  @Override
  public void onApplicationEvent(SessionConnectedEvent sessionConnectedEvent) {
    System.out.println(Objects.requireNonNull(sessionConnectedEvent.getUser()).getName()+" is connected!");
    onlineUserHub.userConnect(Objects.requireNonNull(sessionConnectedEvent.getUser()).getName());
  }
}
