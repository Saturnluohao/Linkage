package com.f4.linkage.webserver.api.admin.model.operation;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @program: Linkage
 * @description: The action of lock or unlock a moment
 * @author: Zijian Zhang
 * @create: 2019/11/13
 **/
@Data
public class LockMomentOperation {
  private Integer momentId;
  private String reason;
  private AdminOperationType operationType;
  private String adminName;
  private Timestamp executeTime;
}
