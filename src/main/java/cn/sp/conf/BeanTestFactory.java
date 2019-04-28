package cn.sp.conf;

import cn.sp.entity.BeanTest;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;

/**
 * Created by 2YSP on 2019/4/28.
 */
@Configuration
public class BeanTestFactory implements FactoryBean<BeanTest> {
    @Nullable
    @Override
    public Class<?> getObjectType() {
        return BeanTest.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Nullable
    @Override
    public BeanTest getObject() throws Exception {
        System.out.println("==========开始创建Bean了");
        BeanTest beanTest = new BeanTest();
        beanTest.setId(1);
        beanTest.setName("test-bean");
        return beanTest;
    }
}
