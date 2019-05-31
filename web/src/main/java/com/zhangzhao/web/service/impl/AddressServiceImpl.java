package com.zhangzhao.web.service.impl;

import com.zhangzhao.web.base.BaseService;
import com.zhangzhao.web.service.AddressService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 地址
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AddressServiceImpl extends BaseService implements AddressService {

}
