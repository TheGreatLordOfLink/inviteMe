package com.jzqm.inviteme.base.service.impl;

import com.jzqm.inviteme.base.entity.User;
import com.jzqm.inviteme.base.mapper.UserMapper;
import com.jzqm.inviteme.base.service.IUserService;
import com.jzqm.inviteme.common.utils.StringUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zhao Jianbo
 * @since 2020-09-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
	@Autowired
	private UserMapper userMapper;
	
	@Override
	//@Cacheable(key="admin8H_login",value="'loginAdmin_'+#phone+'_'+#password")
	public User queryUserByPhone(String phone) {
		
		QueryWrapper<User> wrapper =new QueryWrapper<User>();
		wrapper.eq("phone", phone);
		User user = userMapper.selectOne(wrapper);
					
	
		return user;
	}
	
	@Override
	public int insertOrUpdate(User user)
	{
		String id = user.getId();
		if (null == id || StringUtil.isEmpty(id))
		{
			return userMapper.insert(user);
		}
		else
		{
			return userMapper.updateById(user);
		}
		
	}
	
	@Override
	public User queryUserById(String id)
	{
		return userMapper.selectById(id);		
	}
}
