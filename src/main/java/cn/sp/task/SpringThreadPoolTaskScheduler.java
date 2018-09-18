package cn.sp.task;

import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Author:ship
 * @Description:
 * @Date: Created in 2018/9/18
 */
@Component
public class SpringThreadPoolTaskScheduler implements Runnable{

    private ThreadPoolTaskScheduler scheduler;

    @PostConstruct
    public void start(){
        scheduler = new ThreadPoolTaskScheduler();
        scheduler.initialize();
        scheduler.schedule(this,new CronTrigger("1 * * ? * *"));
    }

    @PreDestroy
    public void stop(){
        scheduler.destroy();
    }


    @Override
    public void run() {
        System.out.println("yes , a task running based on cron expression!@"+new java.util.Date());
    }
}
