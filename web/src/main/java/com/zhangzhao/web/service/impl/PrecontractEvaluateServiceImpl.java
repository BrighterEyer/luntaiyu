package com.zhangzhao.web.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhangzhao.web.base.BaseService;
import com.zhangzhao.web.service.PrecontractEvaluateService;

/**
 * 预约评价
 * 
 * @author Administrator
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PrecontractEvaluateServiceImpl extends BaseService implements PrecontractEvaluateService {

}
