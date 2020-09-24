package com.jzqm.inviteme.common.config;


import com.aliyun.oss.OSSClient;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AliConfig {

    @Value("${aliyun.sms.regionId}")
    private String SMS_REGION_ID;

    @Value("${aliyun.sms.accessKeyId}")
    private String SMS_ACCESSKEYID;

    @Value("${aliyun.sms.accessKeySecret}")
    private String SMS_ACCESSKEYSECRET;

    @Bean
    public IAcsClient getAcsClient(){
        DefaultProfile profile = DefaultProfile.getProfile(SMS_REGION_ID, SMS_ACCESSKEYID, SMS_ACCESSKEYSECRET);
        IAcsClient client = new DefaultAcsClient(profile);
        return client;
    }



}
