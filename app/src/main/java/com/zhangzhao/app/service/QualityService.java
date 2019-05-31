package com.zhangzhao.app.service;

import com.zhangzhao.app.vo.QualityVo;
import com.zhangzhao.common.commonservice.CommonService;
import com.zhangzhao.common.vo.StatusOneVo;
import com.zhangzhao.common.vo.StatusVo;
import com.zhangzhao.common.vo.StatusVoidVo;

public interface QualityService extends CommonService {


    StatusVoidVo saveProcessing(Long userId, String cause, String img, String explains);

    /**
     * 质量处理列表
     */
    StatusVo findAll(Integer page, Integer pageSize);

    /**
     * 质量处理详情
     *
     * @return
     */
    StatusOneVo<QualityVo> findById(Long id);
}
