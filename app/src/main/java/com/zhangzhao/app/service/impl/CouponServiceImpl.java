package com.zhangzhao.app.service.impl;

import com.zhangzhao.app.base.BaseService;
import com.zhangzhao.app.service.CouponService;
import com.zhangzhao.app.vo.CouponVo;
import com.zhangzhao.app.vo.GoodsPropersieVo;
import com.zhangzhao.common.entity.Coupon;
import com.zhangzhao.common.entity.GoodsCommodity;
import com.zhangzhao.common.vo.StatusOneVo;
import com.zhangzhao.common.vo.StatusVoidVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 优惠券
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CouponServiceImpl extends BaseService implements CouponService {

    @Override
    public StatusVoidVo upStatus(Long id, int status) {
        StatusVoidVo vo = new StatusVoidVo();
        couponRepository.upStatus(id, status);
        vo.success();
        return vo;
    }

    @Override
    public StatusVoidVo saveBean(Object object) {
        StatusVoidVo vo = new StatusVoidVo();
        couponRepository.saveAndFlush((Coupon) object);
        vo.success();
        return vo;
    }

    @Override
    public StatusOneVo detail(Long id) {
        StatusOneVo vo = new StatusOneVo();
        Optional<Coupon> optional = couponRepository.findById(id);
        if (optional.isPresent()) {
            CouponVo couponVo = couponMapper.beanToVo(optional.get());
            List<GoodsCommodity> list = goodsCommodityRepository.findByIdIn(optional.get().getGoodId());
            List<GoodsPropersieVo> collect = list.parallelStream().map(goodsCommodityMapper::beanToGoodsPropersieVo).collect(Collectors.toList());
            couponVo.setGoods(collect);
            vo.success(couponVo);
        }
        return vo;
    }
}
