package com.zhangzhao.web.controller;

import com.zhangzhao.common.dto.CouponDto;
import com.zhangzhao.web.base.BaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Coupon", description = "优惠券")
@Controller
@RequestMapping("/web/coupon")
@CrossOrigin
public class CouponController extends BaseService {
    @GetMapping("/find/view")
    @ApiOperation(value = "优惠卷规则设置", notes = "优惠卷规则设置")
    public String coupon() {
        return "coupon/rule";
    }


    @GetMapping("/statistics/list")
    @ApiOperation(value = "优惠卷消费统计", notes = "优惠卷消费统计")
    public String couponList(ModelMap model) {
        model.put("coupon", couponService.statistics());
        return "coupon/list";
    }

    @ResponseBody
    @GetMapping("/list")
    @ApiOperation(value = "优惠卷列表", notes = "优惠卷列表")
    public Object couponList(@RequestParam(required = false, defaultValue = "1") Integer page,
                             @RequestParam(required = false, defaultValue = "10") Integer limit) {
        return couponService.couponList(page, limit).toString();
    }

    @ResponseBody
    @PostMapping("/save")
    @ApiOperation(value = "添加", notes = "添加")
    public Object couponSave(@ApiParam("参数封装") @Valid CouponDto couponDto, BindingResult results) {
        return couponService.couponSave(couponDto, results).toString();
    }


    @ResponseBody
    @PostMapping("/del")
    @ApiOperation(value = "删除", notes = "删除")
    public Object delCoupon(@ApiParam("id") @RequestParam Long id) {
        return couponService.delCoupon(id).toString();
    }

    @ResponseBody
    @PostMapping("/update")
    @ApiOperation(value = "修改", notes = "修改")
    public Object couponUpdate(@ApiParam("参数封装") @Valid CouponDto couponDto, BindingResult results) {

        return couponService.couponUpdate(couponDto, results).toString();
    }

}
