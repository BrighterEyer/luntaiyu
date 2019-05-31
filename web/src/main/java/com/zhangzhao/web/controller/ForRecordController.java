package com.zhangzhao.web.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhangzhao.web.base.BaseService;

import io.swagger.annotations.Api;

@Api(tags = "ForRecord", description = "兑换记录")
@RestController
@RequestMapping("/web/forRecord")
@CrossOrigin
public class ForRecordController extends BaseService {

}
