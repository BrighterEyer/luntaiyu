package com.zhangzhao.app.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhangzhao.app.base.BaseService;
import com.zhangzhao.app.service.PunchCardService;

/**
 * 打卡
 * 
 * @author Administrator
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PunchCardServiceImpl extends BaseService implements PunchCardService {

}
