package com.zhangzhao.web.service.impl;

import com.zhangzhao.web.base.BaseService;
import com.zhangzhao.web.service.OrderDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 订单详情
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OrderDetailsServiceImpl extends BaseService implements OrderDetailsService {

}
