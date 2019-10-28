package com.f4.linkage.webserver.api.login.config;

import com.f4.linkage.webserver.api.login.service.UserService;
import com.f4.linkage.webserver.util.RestfulResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: Linkage
 * @description: Configure authentication and authorization
 * @author: Zijian Zhang
 * @create: 2019/10/26
 **/
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  UserService userService;
  @Bean
  PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception{
    auth.userDetailsService(userService);
  }

  @Bean
  RoleHierarchy roleHierarchy(){
    RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
    String hierarchy = "ROLE_admin > ROLE_user";
    roleHierarchy.setHierarchy(hierarchy);
    return roleHierarchy;
  }


  @Override
  public void configure(WebSecurity web) {
    web.ignoring().antMatchers("/static/index.html", "/static/**", "/favicon.ico","/webjars/**");
  }


  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
      .antMatchers("/admin/**").hasRole("admin")
      .antMatchers("/user/**").hasRole("user")
//      .antMatchers("/login").permitAll()
      .antMatchers("/").permitAll()
      .anyRequest().authenticated()
      .and()
      .formLogin()
      .loginPage("/login/tip")
      .loginProcessingUrl("/login").permitAll()
      .usernameParameter("name")
      .passwordParameter("passwd")
      .successHandler(new AuthenticationSuccessHandler() {
        @Override
        public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
          // TODO 放入配置文件中 session的过期时间
          httpServletRequest.getSession().setMaxInactiveInterval(5*60);
          Map<String,Object> map = new HashMap<>();
          Object principle = authentication.getPrincipal();
          map.put("status",200);
          map.put("msg",principle);
          RestfulResponseHelper.writeToResponse(httpServletResponse,200, map);
        }
      })
      .failureHandler(new AuthenticationFailureHandler() {
        @Override
        public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
          Map<String, Object> map = new HashMap<>();
          map.put("status",401);
          if(e instanceof BadCredentialsException){
            map.put("msg","Wrong username or password!");
          } else if(e instanceof LockedException){
            map.put("msg","Your account is locked!");
          }else if (e instanceof UsernameNotFoundException){
            map.put("msg","Not registered");
          }else {
            map.put("msg","log in failed");
            map.put("debug",e);
          }
          RestfulResponseHelper.writeToResponse(httpServletResponse,401, map);

        }
      })
      .and()
      .logout()
      .logoutSuccessHandler(new LogoutSuccessHandler() {
        @Override
        public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
          Map<String, Object> map = new HashMap<>();
          map.put("status",200);
          map.put("msg","log out success");
          RestfulResponseHelper.writeToResponse(httpServletResponse,200, map);
        }
      })
      .and()
      .csrf().disable();
  }
}
