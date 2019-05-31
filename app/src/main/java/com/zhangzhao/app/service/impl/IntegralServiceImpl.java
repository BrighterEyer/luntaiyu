package com.zhangzhao.app.service.impl;

import com.zhangzhao.app.base.BaseService;
import com.zhangzhao.app.service.IntegralService;
import com.zhangzhao.app.vo.IntegralVo;
import com.zhangzhao.common.entity.Integral;
import com.zhangzhao.common.vo.StatusVo;
import com.zhangzhao.common.vo.StatusVoidVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 积分记录
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class IntegralServiceImpl extends BaseService implements IntegralService {

    @Override
    public StatusVoidVo saveBean(Object object) {
        StatusVoidVo vo=new StatusVoidVo();
        integralRepository.save((Integral)object);
        vo.success();
        return vo;
    }

    @Override
    public StatusVo findAll(Integer page, Integer pageSize, String... p) {
        StatusVo vo=new StatusVo();
        Pageable pageable = PageRequest.of(page-1, pageSize, Sort.Direction.DESC, "createTime");
        Page<Integral> all = integralRepository.findByUserId(getUser().getId(), pageable);
        List<IntegralVo> collect = all.stream().map(integralMapper::beanToVo).collect(Collectors.toList());
        vo.success(collect);
        return vo;
    }
}
