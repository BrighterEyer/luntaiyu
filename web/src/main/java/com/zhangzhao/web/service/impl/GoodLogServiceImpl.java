package com.zhangzhao.web.service.impl;

import com.zhangzhao.web.base.BaseService;
import com.zhangzhao.web.service.AdminLogService;
import com.zhangzhao.web.service.GoodLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 后台商品日志管理
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class GoodLogServiceImpl extends BaseService implements GoodLogService {

}
