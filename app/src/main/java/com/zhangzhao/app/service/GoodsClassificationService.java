package com.zhangzhao.app.service;

import com.zhangzhao.app.vo.GoodsClassificationVo;
import com.zhangzhao.common.commonservice.CommonService;
import com.zhangzhao.common.vo.StatusVo;

public interface GoodsClassificationService extends CommonService {

    StatusVo<GoodsClassificationVo> findAll(Long id);
}
