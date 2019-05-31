package com.zhangzhao.app.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhangzhao.app.base.BaseService;

import io.swagger.annotations.Api;

@Api(tags = "GoodSecurity", description = "商品保障")
@RestController
@RequestMapping("/app/goodSecurity")
public class GoodSecurityController extends BaseService {

}
