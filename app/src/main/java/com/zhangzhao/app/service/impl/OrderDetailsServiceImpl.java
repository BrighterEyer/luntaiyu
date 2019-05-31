package com.zhangzhao.app.service.impl;

import com.zhangzhao.app.base.BaseService;
import com.zhangzhao.app.service.ActivityService;
import com.zhangzhao.app.service.OrderDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 订单详情
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OrderDetailsServiceImpl extends BaseService implements OrderDetailsService {

}
