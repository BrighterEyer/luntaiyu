package com.zhangzhao.app.service;

import com.zhangzhao.common.commonservice.CommonService;
import com.zhangzhao.common.vo.StatusOneVo;
import com.zhangzhao.common.vo.StatusVo;
import com.zhangzhao.common.vo.StatusVoidVo;

public interface PresentService extends CommonService {

    StatusOneVo findAll(Integer page, Integer pageSize);

    StatusOneVo findById(long id);

    StatusVoidVo exchange(long id,long addressId);

    StatusVo exchangeList(Integer page, Integer pageSize);

    StatusOneVo exchangeDetail(long id);
}
