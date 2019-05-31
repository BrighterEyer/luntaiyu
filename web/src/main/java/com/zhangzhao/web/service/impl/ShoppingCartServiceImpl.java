package com.zhangzhao.web.service.impl;

import com.zhangzhao.web.base.BaseService;
import com.zhangzhao.web.service.ShoppingCartService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 购物车
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ShoppingCartServiceImpl extends BaseService implements ShoppingCartService {

}
