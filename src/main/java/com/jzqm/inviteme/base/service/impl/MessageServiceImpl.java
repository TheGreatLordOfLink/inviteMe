package com.jzqm.inviteme.base.service.impl;

import com.jzqm.inviteme.base.entity.Message;
import com.jzqm.inviteme.base.mapper.MessageMapper;
import com.jzqm.inviteme.base.service.IMessageService;
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
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements IMessageService {

}
