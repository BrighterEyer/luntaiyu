package com.zhangzhao.web.service;

import com.zhangzhao.common.commonservice.CommonService;
import com.zhangzhao.common.entity.GoodSecurity;

import java.util.List;

public interface GoodSecurityService extends CommonService {

    List<GoodSecurity> findAll();
}
