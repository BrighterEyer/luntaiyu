package com.zhangzhao.web.service;

import com.zhangzhao.common.commonservice.CommonService;
import com.zhangzhao.common.dto.OutDeliveryDto;
import com.zhangzhao.common.entity.GoodsCommodity;
import com.zhangzhao.common.entity.OutDelivery;
import com.zhangzhao.common.vo.StatusOneVo;
import com.zhangzhao.common.vo.StatusPageVo;
import org.springframework.validation.BindingResult;

public interface OutDeliveryService extends CommonService {
    StatusOneVo<GoodsCommodity> couponUpdate(Long id, int inventory);

    StatusPageVo findAll(Integer page, Integer pageSize, String name);

    StatusPageVo findAll(Integer page, Integer pageSize);

    StatusOneVo<OutDelivery> outinventory(OutDeliveryDto outDeliveryDto, BindingResult result);
}
