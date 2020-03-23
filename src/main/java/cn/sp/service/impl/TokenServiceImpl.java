package cn.sp.service.impl;

import cn.sp.constant.BaseConstants;
import cn.sp.exception.BusinessException;
import cn.sp.service.RedisService;
import cn.sp.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * Created by 2YSP on 2020/3/23.
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private RedisService redisService;


    @Override
    public String createToken() {
        // 使用uuid
        StringBuilder token = new StringBuilder();
        token.append(BaseConstants.TOKEN_PREFIX).append(UUID.randomUUID());
        // 过期时间设置为10s
        redisService.setEx(token.toString(), token.toString(), 10000L);

        return token.toString();
    }

    @Override
    public boolean checkToken(HttpServletRequest request) {
        // 先从header中取
        String token = request.getHeader(BaseConstants.TOKEN_NAME);
        if (StringUtils.isEmpty(token)) {
            token = request.getParameter(BaseConstants.TOKEN_NAME);
        }
        if (StringUtils.isEmpty(token)) {
            throw new BusinessException("http请求中token不存在");
        }
        if (!redisService.exists(token)) {
            throw new BusinessException(1008, "重复的请求");
        }
        // 删除
        boolean flag = redisService.remove(token);
        if (!flag){
            throw new BusinessException(1008, "重复的请求");
        }
        return true;
    }
}
