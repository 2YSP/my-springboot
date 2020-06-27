package cn.sp.service.impl;

import cn.sp.service.TestService;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author 2YSP
 * @date 2020/6/27 16:11
 */
@Slf4j
@Service
public class TestServiceImpl implements TestService {

    /**
     * 通知时间间隔数组
     */
    private static final Integer[] TIME_INTERVAL = new Integer[]{1,3,5,7,11,21,60};
    /**
     * 最大重试次数
     */
    private static final Integer MAX_RETRY_COUNT = 5;

    private final ScheduledExecutorService scheduler;

    public TestServiceImpl(){
        scheduler = Executors.newScheduledThreadPool(3,
                new ThreadFactoryBuilder().setNameFormat("NotifyPayment-%d").setDaemon(true).build());
    }


    /***
     * 模拟支付成功回调
     */
    public void notifyPayment(int currRetryTimes){
        try {
            // 次数+1
            ++currRetryTimes;
            boolean success = doBusiness();
            if (!success){
                retry(currRetryTimes);
            }
        }catch (Exception e){
            retry(currRetryTimes);
        }
    }

    /**
     * 重试
     */
    private void retry(int currRetryTimes){
        if (currRetryTimes > MAX_RETRY_COUNT){
            log.error("已达最大重试次数，不再重试，count:[{}]",currRetryTimes);
            return;
        }
        int timeInterval = TIME_INTERVAL[currRetryTimes-1];
        log.info("将在[{}]秒后重试",timeInterval);
        scheduler.schedule(()-> notifyPayment(currRetryTimes),timeInterval, TimeUnit.SECONDS);
    }

    /**
     * 模拟执行业务逻辑
     * @return
     */
    private boolean doBusiness(){
        log.info(Thread.currentThread().getName() + "执行业务逻辑");
        return false;
    }
}
