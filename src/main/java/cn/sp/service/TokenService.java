package cn.sp.service;


import javax.servlet.http.HttpServletRequest;

/**
 * Created by 2YSP on 2020/3/23.
 */
public interface TokenService {

    /**
     * 生成token
     * @return
     */
    String createToken();

    /**
     * 校验token
     * @param request
     * @return true表示校验通过
     */
    boolean checkToken(HttpServletRequest request);
}
