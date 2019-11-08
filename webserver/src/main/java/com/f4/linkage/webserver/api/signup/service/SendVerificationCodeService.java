package com.f4.linkage.webserver.api.signup.service;

import com.f4.linkage.webserver.api.signup.model.AliYunReply;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @program: Linkage
 * @description: Send verification code with ali yun
 * @author: Zijian Zhang
 * @create: 2019/11/07
 **/
@Service
public class SendVerificationCodeService {
  @Autowired
  RedisTemplate<String,String> redisTemplate;
  private Random random = new Random();
  private RestTemplate restTemplate = new RestTemplate();
  private String getRandomCode(){
    // return between 100000 and 999999
    int random_code = 100000 + random.nextInt(900000);
    return ""+random_code;
  }
  private String getURL(String code, String phoneNumber) throws Exception {
    String accessKeyId = "LTAI4FbjhyPdfkjbdxMp2mzz";
    String accessSecret = "1en5u8ViaqNHvWzGxO4Myq6uq2LAxw";
    java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    df.setTimeZone(new java.util.SimpleTimeZone(0, "GMT"));// 这里一定要设置GMT时区
    java.util.Map<String, String> paras = new java.util.HashMap<String, String>();
    // 1. 系统参数
    paras.put("SignatureMethod", "HMAC-SHA1");
    paras.put("SignatureNonce", java.util.UUID.randomUUID().toString());
    paras.put("AccessKeyId", accessKeyId);
    paras.put("SignatureVersion", "1.0");
    paras.put("Timestamp", df.format(new java.util.Date()));
    paras.put("Format", "json");
    // 2. 业务API参数
    paras.put("Action", "SendSms");
    paras.put("Version", "2017-05-25");
    paras.put("RegionId", "cn-hangzhou");
    paras.put("SignName", "Linkage");
    paras.put("PhoneNumbers", phoneNumber);
    paras.put("TemplateParam", "{\"code\":\""+code+"\"}");
    paras.put("TemplateCode", "SMS_176942940");
    paras.put("OutId", "123");
    // 3. 去除签名关键字Key
    paras.remove("Signature");
    // 4. 参数KEY排序
    java.util.TreeMap<String, String> sortParas = new java.util.TreeMap<String, String>(paras);
    // 5. 构造待签名的字符串
    java.util.Iterator<String> it = sortParas.keySet().iterator();
    StringBuilder sortQueryStringTmp = new StringBuilder();
    while (it.hasNext()) {
      String key = it.next();
      sortQueryStringTmp.append("&").append(specialUrlEncode(key)).append("=").append(specialUrlEncode(paras.get(key)));
    }
    String sortedQueryString = sortQueryStringTmp.substring(1);// 去除第一个多余的&符号
    String stringToSign = "GET" + "&" +
      specialUrlEncode("/") + "&" +
      specialUrlEncode(sortedQueryString);
    String sign = sign(accessSecret + "&", stringToSign);
    // 6. 签名最后也要做特殊URL编码
    String signature = specialUrlEncode(sign);
   return ("http://dysmsapi.aliyuncs.com/?Signature=" + signature + sortQueryStringTmp) ;
  }
  private static String specialUrlEncode(String value) throws Exception {
    return java.net.URLEncoder.encode(value, StandardCharsets.UTF_8).replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
  }
  private static String sign(String accessSecret, String stringToSign) throws Exception {
    javax.crypto.Mac mac = javax.crypto.Mac.getInstance("HmacSHA1");
    mac.init(new javax.crypto.spec.SecretKeySpec(accessSecret.getBytes(StandardCharsets.UTF_8), "HmacSHA1"));
    byte[] signData = mac.doFinal(stringToSign.getBytes(StandardCharsets.UTF_8));
    return Base64.getEncoder().encodeToString(signData);
  }
  private void putCodeIntoRedis(String type, String phoneNumber,String code){
    HashOperations<String,String,String> operations = redisTemplate.opsForHash();
    redisTemplate.expire(type,5, TimeUnit.MINUTES);
    operations.put(type,phoneNumber,code);
  }
  public AliYunReply sendVerificationCode(String phoneNumber,String type) throws Exception {
    String code = getRandomCode();
    String url = getURL(code,phoneNumber);
    URL realUrl = new URL(url);
    // 打开和URL之间的连接
    URLConnection connection = realUrl.openConnection();
    // 设置通用的请求属性
    connection.setRequestProperty("accept", "*/*");
    connection.setRequestProperty("connection", "Keep-Alive");
    connection.setRequestProperty("user-agent",
      "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
    // 建立实际的连接
    connection.connect();
    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    String line;
    StringBuilder result = new StringBuilder();
    while ((line = in.readLine()) != null) {
      result.append(line);
    }
    JSONParser jsonParser = new JSONParser(result.toString());
    LinkedHashMap<String,String> reply = (LinkedHashMap<String,String>) jsonParser.parse();
    AliYunReply aliYunReply = new AliYunReply();
    aliYunReply.setBizId(reply.get("BizId"));
    aliYunReply.setCode(reply.get("Code"));
    aliYunReply.setMessage(reply.get("Message"));
    aliYunReply.setRequestId(reply.get("RequestId"));
    if(aliYunReply.getCode().equals("OK")){
      //System.out.println(phoneNumber+code);
      putCodeIntoRedis(type ,phoneNumber,code);
    }else {
      System.out.println(aliYunReply);
    }
    return aliYunReply;
  }
}
