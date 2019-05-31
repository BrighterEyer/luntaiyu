package com.zhangzhao.web.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhangzhao.web.base.BaseService;
import com.zhangzhao.web.service.MasterTimeService;

/**
 * 师傅上下班时间
 * @author Administrator
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MasterTimeServiceImpl extends BaseService implements MasterTimeService {

}
