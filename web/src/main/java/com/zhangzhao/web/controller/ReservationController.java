package com.zhangzhao.web.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhangzhao.web.base.BaseService;

import io.swagger.annotations.Api;

@Api(tags = "Reservation", description = "预约")
@RestController
@RequestMapping("/web/reservation")
@CrossOrigin
public class ReservationController extends BaseService {

}
