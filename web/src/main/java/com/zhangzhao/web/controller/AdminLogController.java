package com.zhangzhao.web.controller;

import com.zhangzhao.web.base.BaseService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "AdminLog",description = "后台日志")
@RestController
@RequestMapping("/web/adminlog")
@CrossOrigin
public class AdminLogController extends BaseService {

}
