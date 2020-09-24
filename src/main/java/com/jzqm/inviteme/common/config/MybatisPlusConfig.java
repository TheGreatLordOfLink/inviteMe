package com.jzqm.inviteme.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;

/**
 * mybatisPlus配置
 * @author Yven
 *
 */
@Configuration
public class MybatisPlusConfig {

	
	/**
	 * 分页用
	 * @return
	 */
	@Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
	
}
