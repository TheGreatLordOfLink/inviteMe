package com.jzqm.inviteme.base.service;

import com.jzqm.inviteme.base.entity.Code;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Zhao Jianbo
 * @since 2020-09-22
 */
public interface ICodeService extends IService<Code> {
	public List<Code> queryAll();
	
	public int insertOrUpdate(Code entity);
	
	public int delete(String id);
	
	public Code queryById(String id);
	
}
