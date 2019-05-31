package com.zhangzhao.app.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhangzhao.app.base.BaseService;
import com.zhangzhao.app.service.AttentionService;

/**
 * 关注
 * 
 * @author Administrator
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AttentionServiceImpl extends BaseService implements AttentionService {

}
