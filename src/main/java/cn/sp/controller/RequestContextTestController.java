package cn.sp.controller;

import cn.sp.context.ServiceContext;
import cn.sp.context.ServiceContextHolder;
import com.netflix.hystrix.strategy.concurrency.HystrixContextRunnable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 2YSP on 2019/7/28.
 */
@RestController
@RequestMapping("request-context")
@Slf4j
public class RequestContextTestController {

  @RequestMapping(value = "test", method = RequestMethod.GET)
  public String test(@RequestParam("userId") Long userId) {
    System.out.println("请求的用户id:" + userId);

    HystrixContextRunnable runnable =
        new HystrixContextRunnable(() -> {
          //从新的线程中获取当前用户id
          ServiceContext context = ServiceContextHolder.getServiceContext();
          System.out.println("新线程的用户id：" + context.getUserId());
          context.setUserId(110L);
        });

    new Thread(runnable).start();

    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return ServiceContextHolder.getServiceContext().getUserId() + "";
  }
}
