package com.zhangzhao.app.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhangzhao.app.base.BaseService;

import io.swagger.annotations.Api;

@Api(tags = "PunchCard", description = "打卡")
@RestController
@RequestMapping("/app/punchCard")
public class PunchCardController extends BaseService {

}
