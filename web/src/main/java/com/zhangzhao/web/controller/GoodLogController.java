package com.zhangzhao.web.controller;

import com.zhangzhao.web.base.BaseService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "GoodLog",description = "后台商品日志")
@RestController
@RequestMapping("/web/goodlog")
@CrossOrigin
public class GoodLogController extends BaseService {

}
