package com.zhangzhao.web.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhangzhao.web.base.BaseService;
import com.zhangzhao.web.service.ReturnService;

/**
 *  退换货
 * 
 * @author Administrator
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ReturnServiceImpl extends BaseService implements ReturnService {

}
