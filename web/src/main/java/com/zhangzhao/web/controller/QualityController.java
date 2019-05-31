package com.zhangzhao.web.controller;

import com.zhangzhao.web.base.BaseService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Quality",description = "质量处理")
@RestController
@RequestMapping("/web/quality")
@CrossOrigin
public class QualityController extends BaseService {

}
