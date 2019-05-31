package com.zhangzhao.web.controller;

import com.zhangzhao.web.base.BaseService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Complaints",description = "投诉")
@RestController
@RequestMapping("/web/complaints")
@CrossOrigin
public class ComplaintsController extends BaseService {

}
