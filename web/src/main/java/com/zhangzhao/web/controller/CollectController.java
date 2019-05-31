package com.zhangzhao.web.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhangzhao.web.base.BaseService;

import io.swagger.annotations.Api;

@Api(tags = "Collect", description = "收藏")
@RestController
@RequestMapping("/web/collect")
@CrossOrigin
public class CollectController extends BaseService {

}
