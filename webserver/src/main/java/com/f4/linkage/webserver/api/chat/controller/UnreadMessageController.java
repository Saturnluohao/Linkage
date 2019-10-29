package com.f4.linkage.webserver.api.chat.controller;

import com.f4.linkage.webserver.api.chat.model.Message;
import com.f4.linkage.webserver.api.chat.service.MessageService;
import com.f4.linkage.webserver.util.RestfulResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: Linkage
 * @description: Give user unread messages.
 * @author: Zijian Zhang
 * @create: 2019/10/29
 **/
@RestController
public class UnreadMessageController {
  @Autowired
  MessageService messageService;
  @GetMapping("/unreadMessage")
  public void askForLogin(Principal principal, HttpServletResponse httpServletResponse) throws IOException {
    Map<String, Object> map = new HashMap<>();
    List<Message> unreadMessages = messageService.findUnreadMessages(principal.getName());
    map.put("msg",unreadMessages);
    messageService.setMessageAllRead(principal.getName());
    RestfulResponseHelper.writeToResponse(httpServletResponse,200,map);
  }
}
