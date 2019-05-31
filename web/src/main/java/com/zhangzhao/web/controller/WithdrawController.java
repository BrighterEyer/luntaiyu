package com.zhangzhao.web.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhangzhao.web.base.BaseService;

import io.swagger.annotations.Api;

@Api(tags = "Withdraw", description = "提现")
@RestController
@RequestMapping("/web/withdraw")
@CrossOrigin
public class WithdrawController extends BaseService {

}
