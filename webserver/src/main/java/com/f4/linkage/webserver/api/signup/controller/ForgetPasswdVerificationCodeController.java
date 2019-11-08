package com.f4.linkage.webserver.api.signup.controller;

import com.f4.linkage.webserver.api.signup.model.AliYunReply;
import com.f4.linkage.webserver.api.signup.service.SendVerificationCodeService;
import com.f4.linkage.webserver.util.RestfulResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: Linkage
 * @description: To handle forget passwd
 * @author: Zijian Zhang
 * @create: 2019/11/08
 **/
@RestController
public class ForgetPasswdVerificationCodeController {
    @Autowired
    SendVerificationCodeService sendVerificationCodeService;
    @GetMapping("/forget/code")
    void sendVerificationCode(String phone, HttpServletResponse response)  {
      try {
        new AliYunReply();
        AliYunReply reply = sendVerificationCodeService.sendVerificationCode(phone,"forgetPasswdCode");
        if(reply.getCode().equals("OK")){
          Map<String,Object> map = new HashMap<>();
          map.put("code","OK");
          map.put("msg","code is sent");
          RestfulResponseHelper.writeToResponse(response,200,map);
        }else {
          Map<String,Object> map = new HashMap<>();
          map.put("code","Error");
          map.put("msg",reply.getMessage());
          RestfulResponseHelper.writeToResponse(response,401,map);
        }
      }catch (Exception e){
        System.out.println(Arrays.toString(e.getStackTrace()));
      }
    }
}
