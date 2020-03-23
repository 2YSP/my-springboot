package cn.sp.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by 2YSP on 2020/3/1.
 * 编译时注解
 *
 */
@Retention(RetentionPolicy.SOURCE)// 注解只在源码中保留
@Target(ElementType.TYPE)// 用于修饰类
public @interface MyGetter {

    String value() default "";
}
