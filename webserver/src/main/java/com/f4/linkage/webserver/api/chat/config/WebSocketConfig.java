package com.f4.linkage.webserver.api.chat.config;

import com.f4.linkage.webserver.api.chat.controller.event.StompConnectEvent;
import com.f4.linkage.webserver.api.chat.controller.event.StompDisconnectEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @program: Linkage
 * @description: To config online chat, potentially offline chat
 * @author: Zijian Zhang
 * @create: 2019/10/28
 **/
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
  @Override
  public void configureMessageBroker(MessageBrokerRegistry messageBrokerRegistry){
    messageBrokerRegistry.enableSimpleBroker("/topic","/queue");
    messageBrokerRegistry.setApplicationDestinationPrefixes("/app");
//    messageBrokerRegistry.enableStompBrokerRelay("/queue").setRelayHost("localhost").setRelayPort(1234);
  }
  @Override
  public void registerStompEndpoints(StompEndpointRegistry endpointRegistry){
    endpointRegistry.addEndpoint("/chat").withSockJS();
  }

  @Bean
  public StompConnectEvent stompConnectEvent(){
    return new StompConnectEvent();
  }

  @Bean
  public StompDisconnectEvent stompDisconnectEvent(){
    return new StompDisconnectEvent();
  }

}
