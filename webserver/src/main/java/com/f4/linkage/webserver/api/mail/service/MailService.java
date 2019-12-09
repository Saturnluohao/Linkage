package com.f4.linkage.webserver.api.mail.service;

import com.f4.linkage.webserver.api.mail.mapper.MailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * @program: Linkage
 * @description:
 * @author: Zijian Zhang
 * @create: 2019/12/09
 **/
@Component
public class MailService {
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    MailMapper mailMapper;
    public void sendSimpleMail(String from,String to,String subject,String content){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        javaMailSender.send(message);
    }
    public String getUserEmail(String username){
        return mailMapper.getEmailAddress(username);
    }
}
