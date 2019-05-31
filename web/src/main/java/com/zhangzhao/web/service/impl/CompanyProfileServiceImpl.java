package com.zhangzhao.web.service.impl;

import com.zhangzhao.web.base.BaseService;
import com.zhangzhao.web.service.CompanyProfileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 公司简介
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CompanyProfileServiceImpl extends BaseService implements CompanyProfileService {

}
