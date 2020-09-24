package com.jzqm.inviteme.common.utils;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.beans.factory.annotation.Autowired;

public class SMSUtils {



    private static IAcsClient acsClient;

    @Autowired
    public void setAcsClient(IAcsClient acsClient) {
        this.acsClient = acsClient;
    }

    //发送验证码
    public static CommonResponse sendSMS(String phone, String templateCode, String TemplateParam)
    {

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "您邀我");
//        request.putQueryParameter("TemplateCode", "SMS_200470447");
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("TemplateParam", TemplateParam);
        try {
            CommonResponse response = acsClient.getCommonResponse(request);
            return response;
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }

        return null;

    }
}
