package com.zhangzhao.web.controller;

import com.zhangzhao.web.base.BaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(tags = "UserCooperate", description = "用户搭档")
@Controller
@RequestMapping("/web/userCooperate")
@CrossOrigin
public class UserCooperateController extends BaseService {

    @GetMapping("/find/view")
    @ApiOperation(value = "会员与积分体系设置", notes = "会员与积分体系设置")
    public Object memberPoints() {
        return "memberPoints/memberPoints";
    }
}
