package com.zhangzhao.app.controller;

import com.zhangzhao.app.base.BaseService;
import com.zhangzhao.app.vo.ShoppingCartVo;
import com.zhangzhao.common.vo.StatusVo;
import com.zhangzhao.common.vo.StatusVoidVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "ShoppingCart", description = "购物车")
@RestController
@RequestMapping("/app/shoppingcart")
public class ShoppingCartController extends BaseService {

    @GetMapping("/shopping/cart/s/list")
    @ApiOperation(value = "1 购物车商品列表", notes = "购物车商品列表", produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusVo<ShoppingCartVo> findAll() {
        return shoppingCartService.findAlls();
    }

    @PostMapping("/shopping/cart/s/del/ids")
    @ApiOperation(value = "2 删除购物车商品", notes = "删除购物车商品", produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusVoidVo delBeans(@ApiParam(value = "商品id集合", required = true) @RequestParam List<Long> ids) {
        return shoppingCartService.delBean(ids);
    }

    @PostMapping("/shopping/cart/s/update/amount")
    @ApiOperation(value = "3 修改购物车商品", notes = "修改购物车商品", produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusVoidVo upAmount(@ApiParam(value = "商品id", required = true) @RequestParam Long id,
                                 @ApiParam(value = "数量", required = true) @RequestParam Integer amount) {
        return shoppingCartService.upAmount(id, amount);
    }

    @PostMapping("/shopping/cart/s/add/id")
    @ApiOperation(value = "4 添加购物车商品", notes = "添加购物车商品", produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusVoidVo addBeans(@ApiParam(value = "商品id", required = true) @RequestParam Long id,
                                 @ApiParam(value = "类型") @RequestParam(required = false) Integer type,
                                 @ApiParam(value = "安装类型", required = true) @RequestParam String installationType,
                                 @ApiParam(value = "购买数量", required = true) @RequestParam int amount) {
        return shoppingCartService.addBeans(id, type, installationType, amount);
    }
}
