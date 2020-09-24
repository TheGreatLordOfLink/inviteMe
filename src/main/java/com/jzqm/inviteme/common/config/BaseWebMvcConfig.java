package com.jzqm.inviteme.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.jzqm.inviteme.common.interceptor.WebInterceptor;


@Configuration
public class BaseWebMvcConfig implements WebMvcConfigurer{

	/**
	 * 
	 * Cors全局跨域访问配置
	 * @author Yven
	 * @param registry
	 */
	public void addCorsMapping(CorsRegistry registry){
		
		registry.addMapping("/**")
				.allowedHeaders("*")
				.allowedMethods("*")
				.allowCredentials(true)
				.maxAge(1800)
				.allowedOrigins("*");
	}
	
	/**
	 * 
	 * 注册全局拦截器
	 * @author Yven
	 * @param registry
	 */
	public void addInterceptors(InterceptorRegistry registry){
		registry.addInterceptor(new WebInterceptor())
				.addPathPatterns("/**");
	//			.excludePathPatterns("/books");
/*		registry.addInterceptor(new WxLoginStateInterceptor())
				.addPathPatterns("/api/mini/login*");*/
	}
}
