package com.zhangzhao.web.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhangzhao.web.base.BaseService;

import io.swagger.annotations.Api;

@Api(tags = "MasterTime", description = "商品分类")
@RestController
@RequestMapping("/web/masterTime")
@CrossOrigin
public class MasterTimeController extends BaseService {

}
