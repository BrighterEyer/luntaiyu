package com.zhangzhao.app.service.impl;

import com.zhangzhao.app.base.BaseService;
import com.zhangzhao.app.service.FlowService;
import com.zhangzhao.app.vo.FlowVo;
import com.zhangzhao.common.entity.Flow;
import com.zhangzhao.common.vo.StatusVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 流程
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FlowServiceImpl extends BaseService implements FlowService {

    /**
     * 流程
     *
     * @return
     */
    @Override
    public StatusVo<FlowVo> findByUserId(Long userId) {
        StatusVo<FlowVo> vo = new StatusVo<>();
        List<Flow> flow = flowRepository.findByUserId(getUser().getId());
        List<FlowVo> flows = flow.stream().map(flowMapper::beanToVo).collect(Collectors.toList());
        vo.success(flows);
        return vo;
    }

}
