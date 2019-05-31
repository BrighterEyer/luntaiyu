package com.zhangzhao.app.service.impl;

import com.zhangzhao.app.vo.MasterTimeVo;
import com.zhangzhao.common.entity.MasterTime;
import com.zhangzhao.common.vo.StatusOneVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhangzhao.app.base.BaseService;
import com.zhangzhao.app.service.MasterTimeService;

/**
 * 师傅上下班时间
 * @author Administrator
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MasterTimeServiceImpl extends BaseService implements MasterTimeService {

    @Override
    public StatusOneVo<MasterTimeVo> queryCommon(){
        StatusOneVo<MasterTimeVo> vo = new StatusOneVo<>();
        MasterTime masterTime = masterTimeRepository.getOne(1L);
        MasterTimeVo masterTimeVo = masterTimeMapper.beanToVo(masterTime);
        vo.success(masterTimeVo);
        return vo;
    }
}
