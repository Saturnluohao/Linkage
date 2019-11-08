package com.f4.linkage.webserver.api.signup.controller;

import com.f4.linkage.webserver.api.signup.error.SameNameError;
import com.f4.linkage.webserver.api.signup.error.SamePhoneNumberError;
import com.f4.linkage.webserver.api.signup.model.ForgetUserInfo;
import com.f4.linkage.webserver.api.signup.service.ResetPasswdService;
import com.f4.linkage.webserver.api.signup.service.SendVerificationCodeService;
import com.f4.linkage.webserver.api.signup.service.ValidateUserPhoneService;
import com.f4.linkage.webserver.util.RestfulResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: Linkage
 * @description: The user has forgotten his passwd
 * @author: Zijian Zhang
 * @create: 2019/11/08
 **/
@RestController
public class ForgetPasswdController {
  @Autowired
  private ValidateUserPhoneService validateUserPhoneService;
  @Autowired
  private ResetPasswdService resetPasswdService;

  @PostMapping("/forget/user")
  public void changeUserPasswd(@RequestBody ForgetUserInfo forgetUserInfo, HttpServletResponse response) throws IOException {
    boolean if_success = validateUserPhoneService.validatePhoneNumber(forgetUserInfo.getPhoneNumber(),forgetUserInfo.getVerificationCode(),"forgetPasswdCode");
    if (if_success){
        resetPasswdService.resetPasswd(forgetUserInfo);
        String username = resetPasswdService.getUserName(forgetUserInfo.getPhoneNumber());
        Map<String,Object> map = new HashMap<>();
        map.put("code","OK");
        map.put("msg","change passwd success");
        map.put("username",username);
        RestfulResponseHelper.writeToResponse(response,200,map);
        validateUserPhoneService.deleteUsedCode(forgetUserInfo.getPhoneNumber(),"forgetPasswdCode");
        return;
    }
    Map<String,Object> map = new HashMap<>();
    map.put("code","code");
    map.put("msg","invalid verification code");
    RestfulResponseHelper.writeToResponse(response,500,map);
  }
}
