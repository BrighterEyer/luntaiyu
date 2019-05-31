package com.zhangzhao.app.controller;

import com.zhangzhao.app.base.BaseService;
import com.zhangzhao.app.vo.CouponVo;
import com.zhangzhao.app.vo.ShoppingCartVo;
import com.zhangzhao.common.entity.Coupon;
import com.zhangzhao.common.entity.GoodsCommodity;
import com.zhangzhao.common.vo.StatusOneVo;
import com.zhangzhao.common.vo.StatusVo;
import com.zhangzhao.common.vo.StatusVoidVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Api(tags = "Coupon",description = "优惠券")
@RestController
@RequestMapping("/app/coupon")
public class CouponController extends BaseService {

    @GetMapping("/coupon/s/list")
    @ApiOperation(value = "1 用户优惠券列表", notes = "用户优惠券列表",produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusVo<CouponVo> findAll(@ApiParam(value="当前页")@RequestParam(required = false,defaultValue = "1")Integer page,
                                      @ApiParam(value="页数")@RequestParam(required = false,defaultValue = "10")Integer pageSize,
                                      @ApiParam(value = "商品id") @RequestParam(required = false) Long id){
        StatusVo<CouponVo> vo=new StatusVo<>();
        List<Coupon> collect = getUser().getCoupons().stream().filter(o -> o.getStatus() == 0 || o.getStatus() == 1).collect(Collectors.toList());
        List<CouponVo> collect1 = null;
        if (id!=null){
            collect1 = collect.stream().map(o -> {
                List<GoodsCommodity> goodsCommodities = null;
                if (StringUtils.isNotBlank(o.getGoodId())) {
                    List<String> list = Arrays.asList(o.getGoodId().split(","));
                    goodsCommodities = goodsCommodityService.findAllById(list.stream().map(Long::getLong).collect(Collectors.toList()));
                }
                CouponVo couponVo = couponMapper.beanToVo(o);
                if (goodsCommodities != null && goodsCommodities.size() > 0) {
                    couponVo.setGoods(goodsCommodities.parallelStream().map(goodsCommodityMapper::beanToGoodsPropersieVo).collect(Collectors.toList()));
                }
                return couponVo;
            }).collect(Collectors.toList());
        }else {
            collect1 = collect.parallelStream().map(couponMapper::beanToVo).collect(Collectors.toList());
        }
        List<CouponVo> couponVoList = new ArrayList<>();
        int currIdx = (page > 1 ? (page -1) * pageSize : 0);
        for (int i = 0; i < pageSize && i < collect1.size() - currIdx; i++) {
            CouponVo couponVo = collect1.get(currIdx + i);
            couponVoList.add(couponVo);
        }
        vo.success(couponVoList);
        return vo;
    }

    @PostMapping("/coupon/s/update/status")
    @ApiOperation(value = "2 修改优惠券状态", notes = "修改优惠券状态", produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusVoidVo upStatus(@ApiParam(value = "优惠券id", required = true) @RequestParam Long id,
                                 @ApiParam(value = "状态 0-未使用 1-使用", required = true) @RequestParam Integer status) {
        return couponService.upStatus(id,status);
    }

    @GetMapping("/coupon/s/detail/id")
    @ApiOperation(value = "3 用户优惠券详情", notes = "用户优惠券详情",produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusOneVo<CouponVo> detail(@ApiParam(value = "优惠券id", required = true) @RequestParam Long id){
        StatusOneVo<CouponVo> vo=new StatusOneVo<>();
        Optional<CouponVo> first = getUser().getCoupons().parallelStream().filter(o -> o.getId().equals(id)).map(o -> {
            List<GoodsCommodity> goodsCommodities = null;
            if (StringUtils.isNotBlank(o.getGoodId())) {
                List<String> list = Arrays.asList(o.getGoodId().split(","));
                goodsCommodities = goodsCommodityService.findAllById(list.stream().map(Long::getLong).collect(Collectors.toList()));
            }
            CouponVo couponVo = couponMapper.beanToVo(o);
            if (goodsCommodities != null && goodsCommodities.size() > 0) {
                couponVo.setGoods(goodsCommodities.parallelStream().map(goodsCommodityMapper::beanToGoodsPropersieVo).collect(Collectors.toList()));
            }
            return couponVo;
        }).findFirst();
        vo.success(first.get());
        return vo;
    }
}
