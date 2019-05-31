package com.zhangzhao.web.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhangzhao.web.base.BaseService;

import io.swagger.annotations.Api;

@Api(tags = "Wallet", description = "我的钱包")
@RestController
@RequestMapping("/web/wallet")
@CrossOrigin
public class WalletController extends BaseService {

}
