package com.zhangzhao.app.service.impl;

import com.zhangzhao.app.base.BaseService;
import com.zhangzhao.app.service.CarModelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 车型
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CarModelServiceImpl extends BaseService implements CarModelService {

}
