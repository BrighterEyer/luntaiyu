package com.zhangzhao.web.mapper;

import com.zhangzhao.common.dto.WebOrderDto;
import com.zhangzhao.common.entity.OrderSupply;
import com.zhangzhao.web.vo.OrderSupplyVo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * 订单
 */
@Component
@Mapper(componentModel = "spring")
public interface OrderSupplyMapper {

    OrderSupplyVo beanToVo(OrderSupply orderSupply);

    OrderSupply dtoToBean(WebOrderDto webOrderDto);
}
