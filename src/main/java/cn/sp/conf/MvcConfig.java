package cn.sp.conf;

import cn.sp.intercepter.TestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author: 2YSP
 * @Description: 添加拦截器
 * @Date: Created in 2017/12/28
 */
@Configuration
@EnableWebMvc
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Bean
    public TestInterceptor getTestInterceptor(){
        return new TestInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截所有
        registry.addInterceptor(getTestInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }
}
