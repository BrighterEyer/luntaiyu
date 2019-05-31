package com.zhangzhao.web.service.impl;

import com.zhangzhao.common.dto.CouponDto;
import com.zhangzhao.common.entity.Coupon;
import com.zhangzhao.common.vo.StatusOneVo;
import com.zhangzhao.common.vo.StatusPageVo;
import com.zhangzhao.common.vo.StatusVoidVo;
import com.zhangzhao.web.base.BaseService;
import com.zhangzhao.web.service.CouponService;
import com.zhangzhao.web.vo.CouponStatisticsVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 优惠券
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CouponServiceImpl extends BaseService implements CouponService {

    @Override
    public StatusPageVo<Coupon> couponList(Integer page, Integer limit) {
        PageRequest request = new PageRequest(page - 1, limit, Sort.Direction.DESC, "createTime");
        Page<Coupon> all = couponRepository.findAll(request);
        StatusPageVo<Coupon> vo = new StatusPageVo<>();
        vo.success(all.getContent(), all.getTotalElements());
        return vo;
    }

    @Override
    public StatusOneVo<Coupon> couponSave(CouponDto couponDto, BindingResult result) {
        StatusOneVo<Coupon> vo = new StatusOneVo<>();
        if (result.hasErrors()) {
            vo.setMsg("参数错误");
        } else {
            Coupon coupon = new Coupon();
            coupon.setName(couponDto.getName());
            coupon.setType(couponDto.getType());
            coupon.setPrice(couponDto.getPrice());
            String s = couponDto.getEndTime();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = format.parse(s);
                coupon.setEndTime(date);

            } catch (Exception e) {
                e.printStackTrace();
            }
            coupon.setGoodId(couponDto.getGoodId());
            coupon.setUserId(couponDto.getUserId());
            Coupon saveAndFlush = couponRepository.saveAndFlush(coupon);
            vo.success(saveAndFlush);
        }
        return vo;
    }

    @Override
    public StatusVoidVo delCoupon(Long id) {
        StatusVoidVo vo = new StatusVoidVo();
        couponRepository.delCoupon(id);
        vo.success();
        return vo;
    }


    @Override
    public StatusOneVo<Coupon> couponUpdate(CouponDto couponDto, BindingResult results) {
        StatusOneVo<Coupon> vo = new StatusOneVo<>();
        Coupon coupon = new Coupon();
        if (results.hasErrors()) {
            vo.setMsg("参数错误");
        } else {
            try {
                coupon.setId(couponDto.getId());
                coupon.setName(couponDto.getName());
                coupon.setType(couponDto.getType());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = simpleDateFormat.parse(couponDto.getEndTime());
                coupon.setEndTime(date);
                coupon.setPrice(couponDto.getPrice());
                coupon.setGoodId(couponDto.getGoodId());
                coupon.setUserId(couponDto.getUserId());
                coupon.setStatus(couponDto.getStatus());
                Coupon oupon = couponRepository.save(coupon);
                vo.success(oupon);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return vo;
    }

    @Override
    public CouponStatisticsVo statistics() {
        CouponStatisticsVo vo = new CouponStatisticsVo();
        try {
            //优惠卷统计
            vo.setZongJine(couponRepository.sumByPrice(new Coupon().getPrice()));
            vo.setCouponZongshu(couponRepository.countByPrice());
            vo.setCouponDjin(couponRepository.couponByDjin());
            vo.setYiShiYongz(couponRepository.countyiShiYongz());
            vo.setWeiShiYongz(couponRepository.countweiShiYongz());
            vo.setYiGuoQiz(couponRepository.countyiGuoQiz());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vo;

    }

}
