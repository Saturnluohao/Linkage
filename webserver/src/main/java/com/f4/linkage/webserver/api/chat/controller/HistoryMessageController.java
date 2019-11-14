package com.f4.linkage.webserver.api.chat.controller;

import com.f4.linkage.webserver.api.chat.model.Message;
import com.f4.linkage.webserver.api.chat.service.MessageService;
import com.f4.linkage.webserver.util.RestfulResponseHelper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
public class HistoryMessageController {
  @Autowired
  MessageService messageService;
  @GetMapping("/message")
  public void getHistoryMessages(Principal principal,String targetName,int currentPage,int pageSize, HttpServletResponse httpServletResponse) throws IOException {
    Map<String, Object> map = new HashMap<>();
    PageInfo<Message> unreadMessages = messageService.findUnreadMessages(principal.getName(),targetName,currentPage,pageSize);
    map.put("totalPages",unreadMessages.getPages());
    map.put("totalNumber",unreadMessages.getTotal());
    map.put("messageList",unreadMessages.getList());
    // messageService.setMessageAllRead(principal.getName());
    RestfulResponseHelper.writeToResponse(httpServletResponse,200,map);
  }
}
