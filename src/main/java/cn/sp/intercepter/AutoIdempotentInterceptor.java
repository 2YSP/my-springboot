package cn.sp.intercepter;

import cn.sp.annotation.AutoIdempotent;
import cn.sp.exception.BusinessException;
import cn.sp.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 2YSP on 2020/3/23.
 */
@Slf4j
@Component
public class AutoIdempotentInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        // 利用反射校验token
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        AutoIdempotent annotation = handlerMethod.getMethodAnnotation(AutoIdempotent.class);
        if (annotation != null) {
            return tokenService.checkToken(request);
        }
        return true;
    }
}
