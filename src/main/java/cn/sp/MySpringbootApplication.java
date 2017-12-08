package cn.sp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@EnableAutoConfiguration
@MapperScan("cn.sp.dao")//MyBatis 支持
@SpringBootApplication
public class MySpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(MySpringbootApplication.class, args);
	}
}
