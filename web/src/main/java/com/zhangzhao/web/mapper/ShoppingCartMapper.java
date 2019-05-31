package com.zhangzhao.web.mapper;

import com.zhangzhao.common.entity.ShoppingCart;
import com.zhangzhao.web.vo.ShoppingCartVo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * 购物车
 */
@Component
@Mapper(componentModel = "spring")
public interface ShoppingCartMapper {

    ShoppingCartVo beanToVo(ShoppingCart shoppingCart);
}
