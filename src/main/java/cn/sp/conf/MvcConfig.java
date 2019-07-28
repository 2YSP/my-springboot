package cn.sp.conf;

import cn.sp.component.IPAddressArgumentResolver;
import cn.sp.intercepter.ServiceContextInterceptor;
import cn.sp.intercepter.TestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

/**
 * @Author: 2YSP
 * @Description: 添加拦截器
 * @Date: Created in 2017/12/28
 */
@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

  //    @Bean
//    public TestInterceptor getTestInterceptor(){
//        return new TestInterceptor();
//    }
  @Bean
  public ServiceContextInterceptor getServiceContextInterceptor() {
    return new ServiceContextInterceptor();
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    //拦截所有
//        registry.addInterceptor(getTestInterceptor()).addPathPatterns("/**");
    registry.addInterceptor(getServiceContextInterceptor()).addPathPatterns("/request-context/**");
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    registry.addResourceHandler("swagger-ui.html")
        .addResourceLocations("classpath:/META-INF/resources/");
    registry.addResourceHandler("/webjars/**")
        .addResourceLocations("classpath:/META-INF/resources/webjars/");
  }

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    resolvers.add(new IPAddressArgumentResolver());
  }
}
