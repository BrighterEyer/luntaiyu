package com.zhangzhao.app.mapper;

import com.zhangzhao.app.vo.OrderDetailsVo;
import com.zhangzhao.common.entity.GoodsCommodity;
import com.zhangzhao.common.entity.OrderDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

/**
 * 订单详情
 */
@Component
@Mapper(componentModel = "spring")
public interface OrderDetailsMapper {

    OrderDetailsVo beanToVo(OrderDetails orderDetails);

    @Mappings({@Mapping(target = "goodsCommodityId", source = "id")})
    OrderDetails goodToOrderDetails(GoodsCommodity goodsCommodity);
}
