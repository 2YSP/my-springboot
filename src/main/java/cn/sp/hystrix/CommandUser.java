package cn.sp.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 用户服务
 * @author 2YSP
 * @date 2020/7/14 21:58
 */
@Slf4j
public class CommandUser extends HystrixCommand<String> {

    private String userName;

    public CommandUser(String userName){
        super(Setter
                // 服务分组
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("UserGroup"))
                // 线程分组
            .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("UserPool"))
                // 线程池配置
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                        .withCoreSize(10)
                        .withKeepAliveTimeMinutes(5)
                        .withMaxQueueSize(10)
                        .withQueueSizeRejectionThreshold(10000)
                )
                // 线程池隔离
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionIsolationStrategy(
                        HystrixCommandProperties.ExecutionIsolationStrategy.THREAD
                ))
        );
        this.userName = userName;
    }

    @Override
    protected String run() throws Exception {
        log.info("userName=[{}]",userName);
        TimeUnit.MILLISECONDS.sleep(100);
        return "userName"+userName;
    }

    public static void main(String[] args)throws Exception {
        CommandOrder commandPhone = new CommandOrder("手机");
        CommandOrder command = new CommandOrder("电视");

        // 阻塞方式执行
        String execute = commandPhone.execute();
        log.info("execute=[{}]",execute);
        // 异步非阻塞方式执行
        Future<String> queue = command.queue();
        String value = queue.get(200, TimeUnit.MILLISECONDS);
        log.info("value=[{}]",value);

        CommandUser commandUser = new CommandUser("张三");
        String name = commandUser.execute();
        log.info("name=[{}]",name);
    }
}
