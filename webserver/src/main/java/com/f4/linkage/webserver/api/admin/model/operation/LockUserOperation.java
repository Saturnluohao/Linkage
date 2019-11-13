package com.f4.linkage.webserver.api.admin.model.operation;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @program: Linkage
 * @description: The action of lock or unlock user
 * @author: Zijian Zhang
 * @create: 2019/11/13
 **/
@Data
public class LockUserOperation {
  // TODO add a attribute for locking for post moment {or comment}
  private String username;
  private AdminOperationType operationType;
  private String adminName;
  private String reason;
  private Timestamp executeTime;
}
