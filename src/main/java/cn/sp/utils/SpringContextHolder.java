package cn.sp.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Ship
 * @version 1.0.0
 * @description
 * @date 2021/10/26 10:36
 */
@Component
public class SpringContextHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextHolder.applicationContext = applicationContext;
    }


    public static <T> T getBean(Class<T> clazz) {
        Map<String, T> beans = applicationContext.getBeansOfType(clazz);
        return beans.isEmpty() ? null : beans.values().iterator().next();
    }

}
