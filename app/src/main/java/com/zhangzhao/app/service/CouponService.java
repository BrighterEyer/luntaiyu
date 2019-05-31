package com.zhangzhao.app.service;

import com.zhangzhao.common.commonservice.CommonService;
import com.zhangzhao.common.vo.StatusOneVo;
import com.zhangzhao.common.vo.StatusVoidVo;

public interface CouponService extends CommonService {

    StatusVoidVo upStatus(Long id,int status);

    StatusOneVo detail(Long id);
}
