package cn.sp.component;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @Author:ship
 * @Description:
 * @Date: Created in 2019/1/16
 */
@Configuration
public class ApplicationEventHandler implements ApplicationListener<ContextRefreshedEvent> {

    
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        ApplicationContext applicationContext = contextRefreshedEvent.getApplicationContext();
        if (applicationContext.getParent() != null){
            return;
        }

        System.out.println("容器刷新事件触发了。。。。。。。。。。");
    }
}
