package com.zhangzhao.app.service;

import com.zhangzhao.common.commonservice.CommonService;
import com.zhangzhao.common.vo.StatusVoidVo;

public interface CollectService extends CommonService {

    StatusVoidVo save(Long id,Integer type);
}
