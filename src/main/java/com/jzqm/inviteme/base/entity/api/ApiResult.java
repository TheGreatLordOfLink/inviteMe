package com.jzqm.inviteme.base.entity.api;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ApiResult<T> {
	
	/**
	 * 处理请求成功
	 */
	public static final int CODE_SUCCESS = 1000;
	/**
	 * 处理请求失败
	 */
	public static final int CODE_FAIL = 1001;

	/**
	 * 不可传空图片
	 */
	public static final int CODE_FAIL_UPLOAD_EMPTY = 1002;
	/**
	 * 长度过大
	 */
	public static final int CODE_FAIL_UPLOAD_SIZE = 1003;

	/**
	 * 后端异常处理请求失败
	 */
	public static final int CODE_FAIL_EXCEPTION = 9999;
	/**
	 * 超过登录错误次数
	 */
	public static final int LOGIN_FAIL = -1;
	
	/**
	 *无权限处理该请求 
	 */
	public static final int CODE_UNAUTH = -403;
	
	/**
	 *APP端TOKEN失效 
	 */
	public static final int TOKEN_INVALID = 1100;
	
	/**
	 *账号已绑定
	 */
	public static final int ACCOUNT_BIND_CODE = 1101;
	
	/**
	 *账号未绑定
	 */
	public static final int ACCOUNT_UNBIND_CODE = 1102;
	/**
	 *第三方绑定手机号成功,跳转设置密码
	 */
	public static final int BIND_NEW_CODE = 1103;
	/**
	 *第三方绑定手机号成功,跳转登陆
	 */
	public static final int BIND_OLD_CODE = 1104;
	
	/**
	 *需要输入验证码
	 */
	public static final int CODE_VALIDCODE = 1105;
	
	/**
	 *跳过业务逻辑
	 */
	public static final int CODE_CONTINUE_OPERATION = 1106;
	
	/**
	 * 返回状态 code
	 */
	private int code;
	
	/**
	 * 返回文本消息 
	 */
	private String message;
	
	private Integer size;//总页数

	/**
	 * 单条记录
	 */
	private T data;
	
	public ApiResult() {
		// TODO Auto-generated constructor stub
	}
	
	public ApiResult(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	
	public ApiResult(int code, String message, Integer size) {
		super();
		this.code = code;
		this.message = message;
		this.size = size;
	}
	
	public ApiResult(int code, String message, T data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	
}
