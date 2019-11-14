package com.f4.linkage.webserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: Linkage
 * @description: Configure Cors globally.
 * @author: Zijian Zhang
 * @create: 2019/10/26
 **/
@Configuration
public class LinkageServerCorsConfig implements WebMvcConfigurer {
  @Value("${linkage.host}")
  private String hostAddress;
  @Override
  public void addCorsMappings(CorsRegistry registry){
    registry.addMapping("/**")
      .allowedHeaders("*")
      .allowedMethods("*")
      .maxAge(1800)
      .allowedOrigins(hostAddress);
  }

}
