package com.f4.linkage.webserver.api.signup.controller;

import com.f4.linkage.webserver.api.signup.error.SameNameError;
import com.f4.linkage.webserver.api.signup.error.SamePhoneNumberError;
import com.f4.linkage.webserver.api.signup.model.SignUpUserInfo;
import com.f4.linkage.webserver.api.signup.service.AddUserService;
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
 * @description: User sign up with verification code
 * @author: Zijian Zhang
 * @create: 2019/11/07
 **/
@RestController
public class SignUpController {
  @Autowired
  private ValidateUserPhoneService validateUserPhoneService;
  @Autowired
  private AddUserService addUserService;
  @PostMapping("/signup/user")
  public void userSignUp(@RequestBody SignUpUserInfo userInfo, HttpServletResponse response) throws IOException {
      boolean if_success = validateUserPhoneService.validatePhoneNumber(userInfo.getPhoneNumber(),userInfo.getVerificationCode(),"verificationCode");
      if (if_success){
        try {
          addUserService.storeNewUser(userInfo);
        }catch (SameNameError | SamePhoneNumberError sameError){
          Map<String,Object> map = new HashMap<>();
          map.put("code","same");
          map.put("msg",sameError.getMessage());
          RestfulResponseHelper.writeToResponse(response,500,map);
        }
        Map<String,Object> map = new HashMap<>();
        map.put("code","OK");
        map.put("msg","sign up success");
        RestfulResponseHelper.writeToResponse(response,200,map);
        validateUserPhoneService.deleteUsedCode(userInfo.getPhoneNumber(),"verificationCode");
        return;
      }
      Map<String,Object> map = new HashMap<>();
      map.put("code","code");
      map.put("msg","invalid verification code");
      RestfulResponseHelper.writeToResponse(response,500,map);
  }
}
