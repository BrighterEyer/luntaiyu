package com.zhangzhao.web.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhangzhao.web.base.BaseService;

import io.swagger.annotations.Api;

@Api(tags = "PunchCard", description = "打卡")
@RestController
@RequestMapping("/web/punchCard")
@CrossOrigin
public class PunchCardController extends BaseService {

}
