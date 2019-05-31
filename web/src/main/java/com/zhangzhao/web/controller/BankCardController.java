package com.zhangzhao.web.controller;

import com.zhangzhao.web.base.BaseService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "BankCard",description = "银行卡")
@RestController
@RequestMapping("/web/bankcard")
@CrossOrigin
public class BankCardController extends BaseService {

}
