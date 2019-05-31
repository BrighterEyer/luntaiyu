package com.zhangzhao.app.mapper;

import com.zhangzhao.app.vo.ActivityVo;
import com.zhangzhao.app.vo.ShoppingCartVo;
import com.zhangzhao.common.entity.Activity;
import com.zhangzhao.common.entity.GoodsCommodity;
import com.zhangzhao.common.entity.ShoppingCart;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * 购物车
 */
@Component
@Mapper(componentModel = "spring")
public interface ShoppingCartMapper {

    ShoppingCartVo beanToVo(ShoppingCart shoppingCart);

    ShoppingCart goodToCart(GoodsCommodity goodsCommodity);
}
