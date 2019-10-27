package cn.sp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
@EnableAspectJAutoProxy(proxyTargetClass = true,exposeProxy = true)
@MapperScan("cn.sp.dao")//MyBatis 支持
@EnableScheduling//开启定时任务
@SpringBootApplication
public class MySpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(MySpringbootApplication.class, args);
	}
}
