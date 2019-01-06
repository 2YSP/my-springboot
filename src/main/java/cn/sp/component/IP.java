package cn.sp.component;

import java.lang.annotation.*;

/**
 * 获取参数
 * Created by 2YSP on 2019/1/6.
 */
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface IP {

    String name() default "ip";

    boolean required() default true;

    String defaultValue() default "0";
}
