package com.f4.linkage.webserver.api.admin.model.operation;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @program: Linkage
 * @description: The action of lock or unlock a post
 * @author: Zijian Zhang
 * @create: 2019/11/13
 **/
@Data
public class LockPostOperation {
  private Integer postId;
  private String reason;
  private AdminOperationType operationType;
  private String adminName;
  private Timestamp executeTime;
}
