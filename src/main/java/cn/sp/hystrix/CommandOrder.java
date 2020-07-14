package cn.sp.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 *
 * 订单服务
 * @author 2YSP
 * @date 2020/7/14 21:48
 */
@Slf4j
public class CommandOrder extends HystrixCommand<String> {

    private String orderName;


    public CommandOrder(String orderName){
        super(Setter.withGroupKey(
                // 服务分组
                HystrixCommandGroupKey.Factory.asKey("OrderGroup")
        )
                // 线程分组
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("OrderPool"))
                // 线程池配置
                .andThreadPoolPropertiesDefaults(
                        HystrixThreadPoolProperties.Setter()
                                .withCoreSize(10)
                        .withKeepAliveTimeMinutes(5)
                        .withMaxQueueSize(10)
                        .withQueueSizeRejectionThreshold(10000))

                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                    .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD)
                )
        );
        this.orderName = orderName;
    }

    @Override
    protected String run() throws Exception {
        log.info("orderName=[{}]",orderName);
        TimeUnit.MILLISECONDS.sleep(100);
        return "OrderName=" + orderName;
    }
}
