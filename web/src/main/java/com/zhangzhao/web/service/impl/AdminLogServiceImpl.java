package com.zhangzhao.web.service.impl;

import com.zhangzhao.web.base.BaseService;
import com.zhangzhao.web.service.AdminLogService;
import com.zhangzhao.web.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 后台日志管理
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AdminLogServiceImpl extends BaseService implements AdminLogService {

}
