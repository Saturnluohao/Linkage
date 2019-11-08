package com.f4.linkage.webserver.api.signup.model;

import lombok.Data;

/**
 * @program: Linkage
 * @description: The replied message from aliyun
 * @author: Zijian Zhang
 * @create: 2019/11/07
 **/
@Data
public class AliYunReply {
  private String Message;
  private String RequestId;
  private String BizId;
  private String Code;
}
