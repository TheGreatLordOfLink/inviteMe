package com.jzqm.inviteme.base.service.impl;

import com.jzqm.inviteme.base.entity.Config;
import com.jzqm.inviteme.base.mapper.ConfigMapper;
import com.jzqm.inviteme.base.service.IConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements IConfigService {

}
