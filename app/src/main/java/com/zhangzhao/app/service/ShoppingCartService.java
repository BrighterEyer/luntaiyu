package com.zhangzhao.app.service;

import com.zhangzhao.app.vo.ShoppingCartVo;
import com.zhangzhao.common.commonservice.CommonService;
import com.zhangzhao.common.vo.StatusVo;
import com.zhangzhao.common.vo.StatusVoidVo;

public interface ShoppingCartService extends CommonService {

    StatusVoidVo upAmount(Long id,Integer amount);

    StatusVoidVo addBeans(Long id,Integer type,String installationType,int amount);
}
