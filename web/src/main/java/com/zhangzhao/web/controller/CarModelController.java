package com.zhangzhao.web.controller;

import com.zhangzhao.web.base.BaseService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "CarModel",description = "车型")
@RestController
@RequestMapping("/web/carmodel")
@CrossOrigin
public class CarModelController extends BaseService {

}
