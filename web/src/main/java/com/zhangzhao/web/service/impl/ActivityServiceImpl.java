package com.zhangzhao.web.service.impl;

import com.zhangzhao.web.base.BaseService;
import com.zhangzhao.web.service.ActivityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 活动
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ActivityServiceImpl extends BaseService implements ActivityService {

}
