package com.zhangzhao.web.service;

import com.zhangzhao.common.commonservice.CommonService;
import com.zhangzhao.common.dto.CouponDto;
import com.zhangzhao.common.entity.Coupon;
import com.zhangzhao.common.vo.StatusOneVo;
import com.zhangzhao.common.vo.StatusPageVo;
import com.zhangzhao.common.vo.StatusVoidVo;
import com.zhangzhao.web.vo.CouponStatisticsVo;
import org.springframework.validation.BindingResult;

public interface CouponService extends CommonService {

    StatusPageVo<Coupon> couponList(Integer page, Integer limit);

    StatusOneVo<Coupon> couponSave(CouponDto couponDto, BindingResult result);

    StatusVoidVo delCoupon(Long id);

    StatusOneVo<Coupon> couponUpdate(CouponDto couponDto, BindingResult results);

    CouponStatisticsVo statistics();





}
