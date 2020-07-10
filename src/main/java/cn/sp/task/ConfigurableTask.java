package cn.sp.task;

import cn.sp.dao.TaskConfigDao;
import cn.sp.entity.TaskConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author 2YSP
 * @date 2020/7/10 19:43
 */
@Slf4j
@Service
public class ConfigurableTask implements SchedulingConfigurer {

    @Resource
    private TaskConfigDao taskConfigDao;

    private String queryCronExpression(){
        TaskConfig taskConfig = taskConfigDao.queryById(1);
        return taskConfig.getCron();
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(() -> {
            log.info("执行业务逻辑。。。。。");
        }, (triggerContext)->{
            String cron = queryCronExpression();
            // 任务触发，可修改任务的执行周期
            CronTrigger cronTrigger = new CronTrigger(cron);
            Date nextExecutionTime = cronTrigger.nextExecutionTime(triggerContext);
            return nextExecutionTime;
        });
    }
}
