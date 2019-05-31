package com.zhangzhao.web.service.impl;

import com.zhangzhao.common.entity.admin.Authority;
import com.zhangzhao.common.vo.StatusVo;
import com.zhangzhao.web.base.BaseService;
import com.zhangzhao.web.service.ActivityService;
import com.zhangzhao.web.service.AuthorityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 权限
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AuthorityServiceImpl extends BaseService implements AuthorityService {

    @Override
    public StatusVo findAlls() {
        StatusVo<Authority> vo = new StatusVo<>();
        List<Authority> list = authorityRepository.findAll();
        vo.success(list);
        return vo;
    }
}
