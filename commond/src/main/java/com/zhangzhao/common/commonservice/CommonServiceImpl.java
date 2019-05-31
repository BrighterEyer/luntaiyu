package com.zhangzhao.common.commonservice;

import com.zhangzhao.common.vo.StatusOneVo;
import com.zhangzhao.common.vo.StatusVo;
import com.zhangzhao.common.vo.StatusVoidVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class CommonServiceImpl<T> implements CommonService<T>{
    @Override
    public StatusVoidVo saveBean(T object) {
        return null;
    }

    @Override
    public StatusVoidVo delBean(List<? extends Number> ids) {
        return null;
    }

    @Override
    public StatusVo findAll(Integer page, Integer pageSize, String... p) {
        return null;
    }

    @Override
    public StatusVo findAlls() {
        return null;
    }

    @Override
    public StatusOneVo findOne(Long id) {
        return null;
    }
}