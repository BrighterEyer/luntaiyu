package com.zhangzhao.web.service.impl;

import com.zhangzhao.web.base.BaseService;
import com.zhangzhao.web.service.MessagesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 消息
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MessagesServiceImpl extends BaseService implements MessagesService {

}
