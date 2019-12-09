package com.f4.linkage.webserver.api.mail.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MailMapper {
    String getEmailAddress(String username);
}
