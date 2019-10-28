package com.f4.linkage.webserver.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @program: Linkage
 * @description: The helper class SEND JSON RESPONCE
 * @author: Zijian Zhang
 * @create: 2019/10/26
 **/
public class RestfulResponseHelper {
  public static void writeToResponse(HttpServletResponse httpServletResponse, int statusCode, Map<String, Object> map) throws IOException {
    httpServletResponse.setStatus(statusCode);
    httpServletResponse.setContentType("application/json;charset=utf-8");
    PrintWriter writer = httpServletResponse.getWriter();
    ObjectMapper objectMapper = new ObjectMapper();
    writer.write(objectMapper.writeValueAsString(map));
    writer.flush();
    writer.close();
  }
}
