package cn.sp.conf;

import cn.sp.component.IPAddressArgumentResolver;
import cn.sp.intercepter.ServiceContextInterceptor;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.google.common.collect.Lists;
import org.hibernate.validator.HibernateValidator;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.lang.Nullable;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
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
@Import(value = {RestResponseBodyAdvice.class})
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


  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
    FastJsonConfig fastJsonConfig = new FastJsonConfig();
    fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat,
        SerializerFeature.WriteNullListAsEmpty,
        SerializerFeature.WriteNullStringAsEmpty,
        SerializerFeature.WriteNullNumberAsZero,
        SerializerFeature.WriteNullBooleanAsFalse);
    //处理中文乱码
    List<MediaType> mediaTypes = Lists.newArrayList();
    mediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
    converter.setSupportedMediaTypes(mediaTypes);
    converter.setFastJsonConfig(fastJsonConfig);
    //添加
    converters.add(converter);
  }

  @Nullable
  @Override
  public Validator getValidator() {
    LocalValidatorFactoryBean factoryBean = new LocalValidatorFactoryBean();
    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    messageSource.setBasename("valid");
    messageSource.setDefaultEncoding("UTF-8");
    factoryBean.setValidationMessageSource(messageSource);
    factoryBean.setProviderClass(HibernateValidator.class);
    return factoryBean;
  }


}
