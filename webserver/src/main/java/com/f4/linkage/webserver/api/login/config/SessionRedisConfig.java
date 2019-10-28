package com.f4.linkage.webserver.api.login.config;

import com.f4.linkage.webserver.api.login.config.properties.SessionRedisProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @program: Linkage
 * @description: Config redis to share session.
 * @author: Zijian Zhang
 * @create: 2019/10/26
 **/
@EnableRedisHttpSession
public class SessionRedisConfig {

  @Autowired
  private SessionRedisProperty property;

  @Bean
  public RedisConnectionFactory connectionFactory() {
    JedisConnectionFactory factory = new JedisConnectionFactory();
    factory.setHostName(property.getHost());
    factory.setPort(property.getPort());
    factory.setDatabase(property.getDatabase());
    return factory;
  }

}
