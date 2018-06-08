package cn.sp.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: 2YSP
 * @Description:
 * @Date: Created in 2018/1/19
 */
@Component
public class ScheduledTask {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * fixedRate = 5000                     上一次开始执行时间点之后5秒再执行
     * fixedDelay = 5000                    上一次执行完毕时间点之后5秒再执行
     * initialDelay = 1000,fixedRate=5000   第一次延迟1秒后执行，之后按fixedRate的规则每5秒执行一次
     * cron=""                              根据cron表达式执行
     */
    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime(){
//        System.out.println("Now is :"+dateFormat.format(new Date()));
    }
}
