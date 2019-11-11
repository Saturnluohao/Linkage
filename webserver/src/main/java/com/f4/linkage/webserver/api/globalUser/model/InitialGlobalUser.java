package com.f4.linkage.webserver.api.globalUser.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: Linkage
 * @description: The model of global user
 * @author: Zijian Zhang
 * @create: 2019/11/11
 **/
@Data
public class InitialGlobalUser implements Serializable {
  private String username;
  private String iconUrl;
  @JsonIgnore
  private String localUserName;
  @JsonIgnore
  private boolean locked;
}
