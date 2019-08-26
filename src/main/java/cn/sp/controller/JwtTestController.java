package cn.sp.controller;

import cn.sp.entity.PayLoad;
import cn.sp.utils.JwtUtils;
import com.google.gson.Gson;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 2YSP on 2019/8/26.
 */
@Slf4j
@RestController
@RequestMapping("/jwt")
public class JwtTestController {

  @GetMapping("/login")
  public String login(@RequestParam("username") String username,
      @RequestParam("password") String password, HttpServletResponse response) throws Exception {
    if (!checkUserNameAndPwd(username, password)) {
      return "login error:invalid username or password";
    }
    // 过期时间
    Long exp = System.currentTimeMillis() + 20 * 1000;
    PayLoad payLoad = new PayLoad(1L, username, exp);
    String token = JwtUtils.generateToken(payLoad);
    Cookie cookie = new Cookie("token", token);
    // HttpOnly属性来防止Cookie被JavaScript读取，从而避免跨站脚本攻击
    cookie.setHttpOnly(true);
    // 30秒
    cookie.setMaxAge(30);
    response.addCookie(cookie);
    return token;
  }

  @GetMapping("/verify")
  public Boolean verifyToken(HttpServletRequest request) {
    String token = getTokenFromCookie(request);
    if (StringUtils.isBlank(token)){
      return false;
    }
    // 验证签名
    if (!JwtUtils.checkSignature(token)){
      return false;
    }
    PayLoad payLoad = JwtUtils.getPayLoad(token);
    if (payLoad.getExp() < System.currentTimeMillis()){
      // 已过期
      return false;
    }
    Gson gson = new Gson();
    log.info("verify successfully ,payLoad:{} ", gson.toJson(payLoad));
    return true;
  }

  private String getTokenFromCookie(HttpServletRequest request){
    Cookie[] cookies = request.getCookies();
    for(Cookie cookie : cookies){
      if (cookie.getName().equals("token")){
          return cookie.getValue();
      }
    }
    return null;
  }

  private boolean checkUserNameAndPwd(String username, String pwd) {
    if (StringUtils.isBlank(username)) {
      return false;
    }
    if (StringUtils.isBlank(pwd)) {
      return false;
    }
    if (username.equals("admin") && pwd.equals("1234")) {
      return true;
    }
    return false;
  }
}
