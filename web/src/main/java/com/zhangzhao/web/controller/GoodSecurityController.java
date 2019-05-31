package com.zhangzhao.web.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhangzhao.web.base.BaseService;

import io.swagger.annotations.Api;

@Api(tags = "GoodSecurity", description = "商品保障")
@RestController
@RequestMapping("/web/goodSecurity")
@CrossOrigin
public class GoodSecurityController extends BaseService {

}
