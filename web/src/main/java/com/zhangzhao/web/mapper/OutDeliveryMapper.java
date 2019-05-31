package com.zhangzhao.web.mapper;

import com.zhangzhao.common.entity.OutDelivery;
import com.zhangzhao.web.vo.OutDeliveryVo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * 出库
 */
@Component
@Mapper(componentModel = "spring")
public interface OutDeliveryMapper {

    OutDeliveryVo beanToVo(OutDelivery outDelivery);

}
