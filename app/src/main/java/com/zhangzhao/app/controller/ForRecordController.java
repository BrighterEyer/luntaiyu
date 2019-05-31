package com.zhangzhao.app.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhangzhao.app.base.BaseService;

import io.swagger.annotations.Api;

@Api(tags = "ForRecord", description = "兑换记录")
@RestController
@RequestMapping("/app/forRecord")
public class ForRecordController extends BaseService {

}
