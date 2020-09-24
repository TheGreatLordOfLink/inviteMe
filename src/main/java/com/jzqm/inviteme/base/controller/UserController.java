package com.jzqm.inviteme.base.controller;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jzqm.inviteme.base.entity.api.ApiResult;
import com.jzqm.inviteme.base.entity.Code;
import com.jzqm.inviteme.base.entity.User;
import com.jzqm.inviteme.base.service.ICodeService;
import com.jzqm.inviteme.base.service.IUserService;
import com.jzqm.inviteme.common.utils.SMSUtils;
import com.jzqm.inviteme.common.utils.StringUtil;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonResponse;
import com.jzqm.inviteme.base.controller.UserController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Zhao Jianbo
 * @since 2020-09-22
 */
@Api(tags = "账号系统及个人中心")
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
	

	/**
	 * 处理请求成功
	 */
	
    @Autowired
    private IUserService userService;
    
    @Autowired
    private ICodeService codeService;
          
    
	@ApiOperation(httpMethod = "POST", value = "后台登录")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "系统错误"),
            @ApiResponse(code = 200, message = "200成功,其它为错误,返回格式：{code:0,message:'',data:object} data 属性参照下方Model",
                    response = ApiResult.class) })
    @ApiImplicitParams(value = {
    		
    })
    @PostMapping("/login")
    @ResponseBody
    public ApiResult loginAdmin( @ApiParam(name = "phone", value = "phone", required = true) String phone,
                                @ApiParam(name = "password", value = "password", required = true) String password) {
    	
    	
		try
		{
	    	if (StringUtil.isPhone(phone))
	    	{
	    		if (!StringUtil.isEmpty(password) && password.length() < 32 )
	    		{	    			
	    			User user = userService.queryUserByPhone(phone);
	    			
	    			if(null != user)
	    			{
	    				String tempP = DigestUtils.md5DigestAsHex(password.getBytes());
	    				if (tempP.equals(user.getPassword()))
	    				{
	                        Map<String,String> data = new HashMap();
	                        
	                        data.put("userId", user.getId());
	                        
	                        user.setLastLoginTime(new Date());
	                                                
	                        userService.updateById(user);
	                        
	    					log.info("用户登录成功"+tempP);
	    			    	return new ApiResult(ApiResult.CODE_SUCCESS,"登录成功",data);
	    				}
	    				else
	    				{
	                        log.info("用户名密码不匹配"+tempP);
	                        return new ApiResult(ApiResult.CODE_FAIL,"用户名密码不匹配");    			    				
	    				}    					
	    			}
	    			else
	    			{
	                    log.info("用户不存在"+phone);
	                    return new ApiResult(ApiResult.CODE_FAIL,"未查找到用户，请联系管理员申请合作");    			    				
	    			}    			
	    		}
	    		else
	    		{
	                log.info("密码为空或太长"+password);
	                return new ApiResult(ApiResult.CODE_FAIL,"密码为空或太长，请重新输入");    			
	    		}
	    	}
	    	else
	    	{
	            log.info("登录手机号格式不匹配="+phone);
	            return new ApiResult(ApiResult.CODE_FAIL,"登录手机号格式不匹配，请重新输入");
	    	}
		}catch(Exception e){
			log.info("登录接口异常"+e.getMessage());
			return new ApiResult(ApiResult.CODE_FAIL_EXCEPTION,"error: "+ApiResult.CODE_FAIL_EXCEPTION+"登录接口异常");
		}
				    
    }
	
	@ApiOperation(httpMethod = "GET", value = "获取用户信息")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "系统错误"),
            @ApiResponse(code = 200, message = "200成功,其它为错误,返回格式：{code:0,message:'',data:object} data 属性参照下方Model",
                    response = ApiResult.class) })
    @ApiImplicitParams(value = {
    		
    })
    @GetMapping("/info")
    @ResponseBody
    public ApiResult info( @ApiParam(name = "userId", value = "userId", required = true) String userId) {
		
		
		if (StringUtil.isEmpty(userId))
		{
            log.info("未获取到userId，userId="+userId);
            return new ApiResult(ApiResult.CODE_FAIL,"目前处于理想状态，请重新登录");
		}
		else
		{
			
			User user = userService.queryUserById(userId);
			if (null != user)
			{
		    	return new ApiResult(ApiResult.CODE_SUCCESS,"获取成功",user);			
			}
			else
			{
	            log.info("获取用户信息失败="+userId);
	            return new ApiResult(ApiResult.CODE_FAIL,"无法获取到用户信息");				
			}
			
		}
	
	}
	
	@ApiOperation(httpMethod = "POST", value = "发送验证码")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "系统错误"),
            @ApiResponse(code = 200, message = "200成功,其它为错误,返回格式：{code:0,message:'',data:object} data 属性参照下方Model",
                    response = ApiResult.class) })
    @ApiImplicitParams(value = {
    		
    })
    @PostMapping("/sendCode")
    @ResponseBody
    public ApiResult sendCode( @ApiParam(name = "phone", value = "phone", required = true) String phone) {
	
    	String codeStr = StringUtil.createCode();

		
    	if (StringUtil.isPhone(phone))
    	{
	    	    	
	    	log.info("发送验证码,code =  " + codeStr);
	    	CommonResponse res =  SMSUtils.sendSMS(phone, "SMS_200470447", "{\"code\":\""+codeStr+"\"}");	    	
	    		    	
	    	if (null != res)
	    	{
		    	JSONObject obj = JSONObject.parseObject(res.getData());
	    		if (obj.get("Message").equals("OK")){
	    			
	    	    	Code temp = new Code();
	    			temp.setPhone(phone);
	    			temp.setCode(codeStr);
	    			temp.setMessage(res.getData());
	    			
	                if (0 != codeService.insertOrUpdate(temp))
	                {
    					log.info("验证码发送成功");
    			    	return new ApiResult(ApiResult.CODE_SUCCESS,"验证码发送成功",temp);
	                }
	                else
	                {
    					log.info("验证码插入数据库失败");
    			    	return new ApiResult(ApiResult.CODE_FAIL,"验证码发送成功",temp);
	                }
	        	}   
	        	else
	        	{
	        		log.info("短信接口返回错误信息");
			    	return new ApiResult(ApiResult.CODE_FAIL,"验证码发送异常请重试");
	        	}
	        	
	        	
	    	}
	    	else
	    	{
	            log.info("短信接口返回null");
		    	return new ApiResult(ApiResult.CODE_FAIL,"验证码发送异常请重试");
	    	}
	    	   	
    	}
    	else
    	{
    		log.info("手机格式不符合");
	    	return new ApiResult(ApiResult.CODE_FAIL,"您输入的手机号不符合规则");
    	}
 
	}


}
