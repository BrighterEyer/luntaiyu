package com.zhangzhao.app.controller;

import com.zhangzhao.app.base.BaseService;
import com.zhangzhao.common.dto.InvoiceDto;
import com.zhangzhao.common.vo.StatusOneVo;
import com.zhangzhao.common.vo.StatusVoidVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Invoice", description = "发票")
@RestController
@RequestMapping("/app/invoice")
public class InvoiceController extends BaseService {

    @PostMapping("/invoice/s/add")
    @ApiOperation(value = "1 添加发票", notes = "添加发票", produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusOneVo saveInvoice(@ApiParam(value = "请求参数封装") @RequestBody @Valid InvoiceDto invoiceDto, BindingResult bindingResult) {
        return invoiceService.saveInvoice(invoiceDto, bindingResult);
    }
}
