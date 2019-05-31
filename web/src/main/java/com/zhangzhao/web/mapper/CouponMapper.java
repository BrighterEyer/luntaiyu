package com.zhangzhao.web.mapper;

import com.zhangzhao.common.dto.CouponDto;
import com.zhangzhao.common.entity.Coupon;
import com.zhangzhao.web.vo.CouponVo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * 优惠卷
 */
@Component
@Mapper(componentModel = "spring")
public interface CouponMapper {


    CouponVo beanToVo(Coupon coupon);

    Coupon dtoTobean(CouponDto couponDto);
}
