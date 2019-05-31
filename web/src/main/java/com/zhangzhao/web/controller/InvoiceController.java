package com.zhangzhao.web.controller;

import com.zhangzhao.web.base.BaseService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Invoice",description = "发票")
@RestController
@RequestMapping("/web/invoice")
@CrossOrigin
public class InvoiceController extends BaseService {

}
