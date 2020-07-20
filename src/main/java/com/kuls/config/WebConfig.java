package com.kuls.config;
import com.kuls.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author kuls
 * @Desc kuls
 * @date 2020/5/4 6:49 下午
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加拦截器LoginInterceptor（自定义的）
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/admin/**")
                .excludePathPatterns("/admin").excludePathPatterns("/admin/login"); // 不包括/admin/login和/admin 用于显示登陆页面和登陆请求

    }


}
