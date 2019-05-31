package com.zhangzhao.web.service.impl;

import com.zhangzhao.web.base.BaseService;
import com.zhangzhao.web.service.ComplaintsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 投诉
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ComplaintsServiceImpl extends BaseService implements ComplaintsService {

}
