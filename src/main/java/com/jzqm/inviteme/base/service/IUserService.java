package com.jzqm.inviteme.base.service;

import com.jzqm.inviteme.base.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Zhao Jianbo
 * @since 2020-09-22
 */
public interface IUserService extends IService<User> {
	/**
	 * 用户登录
	 * @param phone
	 * @param password
	 * @return
	 */
	public User queryUserByPhone(String phone);
	
	public int insertOrUpdate(User user);
	
	public User queryUserById(String id);

}
