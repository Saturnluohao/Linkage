package com.f4.linkage.webserver.api.mail.model;

import lombok.Data;

/**
 * @program: Linkage
 * @description:
 * @author: Zijian Zhang
 * @create: 2019/12/09
 **/
@Data
public class Mail {
    private String content;
    private String targetName;
}
