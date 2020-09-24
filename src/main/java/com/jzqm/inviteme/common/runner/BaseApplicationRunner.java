package com.jzqm.inviteme.common.runner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


/**
 * 系统服务启动
 * @author Yven
 *
 */
@Component
@Order(1)
@Slf4j
public class BaseApplicationRunner implements  ApplicationRunner{
	
	@Value("${spring.application.name}")
	private String serviceName;
	public void run(ApplicationArguments args){
		
		log.info("服务-> [{}] 启动完毕",serviceName);
	}

}