package com.jzqm.inviteme;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
@EnableTransactionManagement
@MapperScan("com.jzqm.inviteme.base.mapper")
public class InviteMeApplication {

    public static void main(String[] args) {
        SpringApplication.run(InviteMeApplication.class, args);
    }

}
