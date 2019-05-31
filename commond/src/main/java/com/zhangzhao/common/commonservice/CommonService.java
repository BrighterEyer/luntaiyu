package com.zhangzhao.common.commonservice;


import com.zhangzhao.common.vo.StatusOneVo;
import com.zhangzhao.common.vo.StatusVo;
import com.zhangzhao.common.vo.StatusVoidVo;

import java.util.List;

public interface CommonService<T> {

    StatusVoidVo saveBean(T object);

    StatusVoidVo delBean(List<? extends Number> ids);

    StatusVo findAll(Integer page, Integer pageSize, String... p);

    StatusVo findAlls();

    StatusOneVo findOne(Long id);
}