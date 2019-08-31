package cn.sp.intercepter;

import cn.sp.context.ServiceContext;
import cn.sp.context.ServiceContextHolder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Created by 2YSP on 2019/7/28.
 */
@Slf4j
public class ServiceContextInterceptor extends HandlerInterceptorAdapter {


  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    initServiceContext(request, request.getRequestURL().toString());
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      @Nullable ModelAndView modelAndView) throws Exception {
    ServiceContextHolder.destroy();
  }

  private void initServiceContext(HttpServletRequest request, String url) {
    ServiceContext serviceContext = new ServiceContext();
    String userId = request.getParameter("userId");
    serviceContext.setUserId(Long.valueOf(userId));
    ServiceContextHolder.setServiceContext(serviceContext);
  }
}
