package com.zhangzhao.app.controller;

import com.zhangzhao.app.base.BaseService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Withdraw", description = "提现")
@RestController
@RequestMapping("/app/withdraw")
public class WithdrawController extends BaseService {


}
