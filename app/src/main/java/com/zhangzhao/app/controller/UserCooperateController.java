package com.zhangzhao.app.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhangzhao.app.base.BaseService;

import io.swagger.annotations.Api;

@Api(tags = "UserCooperate", description = "用户搭档")
@RestController
@RequestMapping("/app/userCooperate")
public class UserCooperateController extends BaseService {

}
