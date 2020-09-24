package com.jzqm.inviteme.base.service.impl;

import com.jzqm.inviteme.base.entity.Code;
import com.jzqm.inviteme.base.mapper.CodeMapper;
import com.jzqm.inviteme.base.service.ICodeService;
import com.jzqm.inviteme.common.utils.StringUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;
import java.util.List;

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
public class CodeServiceImpl extends ServiceImpl<CodeMapper, Code> implements ICodeService {
	@Autowired
	private CodeMapper CodeMapper;
	
	@Override
	public List<Code> queryAll()
	{
		QueryWrapper<Code> queryWrapper =new QueryWrapper<Code>();
		queryWrapper.orderByDesc("create_time");

		return CodeMapper.selectList(queryWrapper);
		
	}
	
	@Override
	public int insertOrUpdate(Code entity) {
		String id = entity.getId();
					
		if (null == id || StringUtil.isEmpty(id))
		{
			entity.setCreateTime(new Date());
			return CodeMapper.insert(entity);
			
		}
		else
		{
			return CodeMapper.updateById(entity);
		}
		
	}
	
	@Override
	public int delete(String id) {
		return CodeMapper.deleteById(id);
	}
	
	public Code queryById(String id)
	{
		return CodeMapper.selectById(id);		
	}

}
