package com.zhangzhao.web.service;

import com.zhangzhao.common.commonservice.CommonService;
import com.zhangzhao.common.entity.Properties;

import java.util.List;

public interface PropertiesService extends CommonService {

    List<Properties> findAll();
}
