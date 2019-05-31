package com.zhangzhao.app.mapper;

import com.zhangzhao.app.vo.CommentsVo;
import com.zhangzhao.app.vo.CouponVo;
import com.zhangzhao.common.entity.Comments;
import com.zhangzhao.common.entity.Coupon;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * 优惠券
 * 
 * @author Administrator
 *
 */
@Component
@Mapper(componentModel = "spring")
public interface CouponMapper {

	CouponVo beanToVo(Coupon coupon);
}
