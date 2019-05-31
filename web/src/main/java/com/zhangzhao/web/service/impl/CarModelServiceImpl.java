package com.zhangzhao.web.service.impl;

import com.zhangzhao.web.base.BaseService;
import com.zhangzhao.web.service.CarModelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 车型
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CarModelServiceImpl extends BaseService implements CarModelService {

}
