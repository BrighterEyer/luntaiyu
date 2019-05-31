package com.zhangzhao.web.service.impl;

import com.zhangzhao.web.base.BaseService;
import com.zhangzhao.web.service.QualityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 质量处理
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class QualityServiceImpl extends BaseService implements QualityService {

}
