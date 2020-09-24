package com.jzqm.inviteme.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局拦截器
 * @author Yven
 *
 */
public class WebInterceptor implements HandlerInterceptor{
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler){
		
		return true;
	}
	
	public void postHandle(HttpServletRequest request,HttpServletResponse response,Object handler,ModelAndView modelAndView){
		
		
	}
	
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response,Object handler,Exception ex){
		
	}
	

}