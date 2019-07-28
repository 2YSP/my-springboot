package cn.sp.context;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariableDefault;

/**
 * Created by 2YSP on 2019/7/28.
 */
public class ServiceContextHolder {

  private static final HystrixRequestVariableDefault<ServiceContext> context = new HystrixRequestVariableDefault<>();


  public static ServiceContext getServiceContext() {
    initServiceContext();
    return context.get();
  }

  public static void setServiceContext(ServiceContext serviceContext) {
    initServiceContext();
    context.set(serviceContext);
  }

  private static void initServiceContext() {
    if (!HystrixRequestContext.isCurrentThreadInitialized()) {
      HystrixRequestContext.initializeContext();
    }
  }

  public static void destory() {
    if (HystrixRequestContext.isCurrentThreadInitialized()) {
      HystrixRequestContext.getContextForCurrentThread().shutdown();
    }
  }
}
