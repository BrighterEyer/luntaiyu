package com.zhangzhao.app.mapper;

import com.zhangzhao.app.vo.OrderSupplyDetailVo;
import com.zhangzhao.common.dto.OrderSupplyDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.zhangzhao.app.vo.OrderSupplyVo;
import com.zhangzhao.common.entity.OrderSupply;

/**
 * 订单
 */
@Component
@Mapper(componentModel = "spring")
public interface OrderSupplyMapper {

    OrderSupplyVo beanToVo(OrderSupply orderSupply);

    OrderSupply dtoToBean(OrderSupplyDto orderSupplyDto);

    OrderSupplyDetailVo beanToOrderSupplyDetailVo(OrderSupply orderSupply);
}
