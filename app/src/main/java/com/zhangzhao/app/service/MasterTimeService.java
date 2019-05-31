package com.zhangzhao.app.service;

import com.zhangzhao.app.vo.MasterTimeVo;
import com.zhangzhao.common.commonservice.CommonService;
import com.zhangzhao.common.vo.StatusOneVo;

public interface MasterTimeService extends CommonService {

    StatusOneVo<MasterTimeVo> queryCommon();
}
