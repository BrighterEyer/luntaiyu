package com.zhangzhao.web.controller;

import com.zhangzhao.web.base.BaseService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "ShoppingCart",description = "购物车")
@RestController
@RequestMapping("/web/shoppingcart")
@CrossOrigin
public class ShoppingCartController extends BaseService {

}
