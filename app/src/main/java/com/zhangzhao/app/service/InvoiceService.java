package com.zhangzhao.app.service;

import com.zhangzhao.common.commonservice.CommonService;
import com.zhangzhao.common.dto.InvoiceDto;
import com.zhangzhao.common.vo.StatusOneVo;
import com.zhangzhao.common.vo.StatusVoidVo;
import org.springframework.validation.BindingResult;

public interface InvoiceService extends CommonService {

    StatusOneVo saveInvoice(InvoiceDto invoiceDto, BindingResult bindingResult);
}
