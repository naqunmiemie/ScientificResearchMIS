package org.yjn.mis.configuration;

import org.yjn.mis.interceptor.AdminLoginInterceptor;
import org.yjn.mis.interceptor.HostInfoInterceptor;
import org.yjn.mis.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class MISWebConfiguration implements WebMvcConfigurer {

  @Autowired
  private LoginInterceptor loginInterceptor;

  @Autowired
  private AdminLoginInterceptor adminLoginInterceptor;

  @Autowired
  private HostInfoInterceptor hostInfoInterceptor;

  @Bean
  public WebMvcConfigurer webMvcConfigurer() {
    return new WebMvcConfigurer() {
      /**
       * 添加拦截器
       */
      @Override
      public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(hostInfoInterceptor).addPathPatterns("/**");
        registry.addInterceptor(loginInterceptor).addPathPatterns("/user/**")
                .excludePathPatterns("/user/login/**").excludePathPatterns("/user/register/**");
        registry.addInterceptor(adminLoginInterceptor).addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/login/**").excludePathPatterns("/admin/register/**");

      }
    };
  }
}
