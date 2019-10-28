package com.f4.linkage.webserver.api.login.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: Linkage
 * @description: Role of user
 * @author: Zijian Zhang
 * @create: 2019/10/26
 **/
@Data
public class Role implements Serializable {
  private Integer id;
  private String name;
}
