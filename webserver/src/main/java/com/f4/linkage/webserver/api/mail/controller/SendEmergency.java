package com.f4.linkage.webserver.api.mail.controller;

import com.f4.linkage.webserver.api.mail.model.Mail;
import com.f4.linkage.webserver.api.mail.service.MailService;
import com.f4.linkage.webserver.util.RestfulResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: Linkage
 * @description:
 * @author: Zijian Zhang
 * @create: 2019/12/09
 **/
@Controller
public class SendEmergency {
    @Autowired
    MailService service;
    @PostMapping("/user/emergency")
    public void sendEmailToEmergencyChat(@RequestBody Mail mail, Principal principal, HttpServletResponse response) throws IOException {
        String email = service.getUserEmail(mail.getTargetName());
        service.sendSimpleMail("buffalo139@foxmail.com",email,"[Linkage] Info from " + principal.getName(),mail.getContent());
        Map<String,Object> map = new HashMap<>();
        map.put("msg","ok");
        RestfulResponseHelper.writeToResponse(response,200,map);
    }
}
