package com.xu.server.base.config;

import cn.dev33.satoken.interceptor.SaAnnotationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 开启sa-token 注解鉴权
 * @author Author
 * @version 0.1
 * Created On 2022/5/12 10:30
 */
@Configuration
public class SaTokenAnnotationConfig implements WebMvcConfigurer {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new SaAnnotationInterceptor()).addPathPatterns("/**");
	}
}
