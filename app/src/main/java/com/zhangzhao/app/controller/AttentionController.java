package com.zhangzhao.app.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhangzhao.app.base.BaseService;

import io.swagger.annotations.Api;

@Api(tags = "Attention", description = "关注")
@RestController
@RequestMapping("/app/attention")
public class AttentionController extends BaseService {

}
