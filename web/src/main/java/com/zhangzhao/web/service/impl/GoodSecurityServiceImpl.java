package com.zhangzhao.web.service.impl;

import com.zhangzhao.common.entity.GoodSecurity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhangzhao.web.base.BaseService;
import com.zhangzhao.web.service.GoodSecurityService;

import java.util.List;

/**
 *  商品保障
 * @author Administrator
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class GoodSecurityServiceImpl extends BaseService implements GoodSecurityService {

    @Override
    public List<GoodSecurity> findAll() {
        return goodSecurityRepository.findAll();
    }
}
