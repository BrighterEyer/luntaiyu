package com.zhangzhao.web.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhangzhao.web.base.BaseService;

import io.swagger.annotations.Api;

@Api(tags = "Return", description = "退换货")
@RestController
@RequestMapping("/web/return")
@CrossOrigin
public class ReturnController extends BaseService {

}
