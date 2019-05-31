package com.zhangzhao.web.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhangzhao.web.base.BaseService;

import io.swagger.annotations.Api;

@Api(tags = "PrecontractEvaluate", description = "预约评价")
@RestController
@RequestMapping("/web/precontractEvaluate")
@CrossOrigin
public class PrecontractEvaluateController extends BaseService {

}
