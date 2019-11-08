package com.f4.linkage.webserver.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: Linkage
 * @description: Configure Cors globally.
 * @author: Zijian Zhang
 * @create: 2019/10/26
 **/
public class LinkageServerCorsConfig implements WebMvcConfigurer {
  @Override
  public void addCorsMappings(CorsRegistry registry){
    registry.addMapping("/**")
      .allowedHeaders("*")
      .allowedMethods("*")
      .maxAge(1800)
      .allowedOrigins("http://localhost:8080");
    // TODO: put this value into application.yml
  }

}
