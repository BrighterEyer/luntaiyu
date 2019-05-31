package com.zhangzhao.app.service;

import com.zhangzhao.app.vo.FlowVo;
import com.zhangzhao.common.commonservice.CommonService;
import com.zhangzhao.common.vo.StatusVo;

public interface FlowService extends CommonService {

    StatusVo<FlowVo> findByUserId(Long userId);
}
