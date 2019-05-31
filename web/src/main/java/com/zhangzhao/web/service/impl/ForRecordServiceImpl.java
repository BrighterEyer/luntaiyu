package com.zhangzhao.web.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhangzhao.web.base.BaseService;
import com.zhangzhao.web.service.ForRecordService;

/**
 *  兑换记录
 * 
 * @author Administrator
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ForRecordServiceImpl extends BaseService implements ForRecordService {

}
