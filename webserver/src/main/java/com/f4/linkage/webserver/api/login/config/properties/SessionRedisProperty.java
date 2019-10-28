package com.f4.linkage.webserver.api.login.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @program: Linkage
 * @description: Properties of redis(session)
 * @author: Zijian Zhang
 * @create: 2019/10/26
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "spring.redis.session")
public class SessionRedisProperty {
  private String host;

  private int port;

  private int database;
}
