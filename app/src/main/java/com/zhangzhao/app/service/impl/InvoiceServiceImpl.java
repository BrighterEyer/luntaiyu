package com.zhangzhao.app.service.impl;

import com.zhangzhao.app.base.BaseService;
import com.zhangzhao.app.service.ActivityService;
import com.zhangzhao.app.service.InvoiceService;
import com.zhangzhao.common.constant.ErrorCode;
import com.zhangzhao.common.dto.InvoiceDto;
import com.zhangzhao.common.entity.Invoice;
import com.zhangzhao.common.vo.StatusOneVo;
import com.zhangzhao.common.vo.StatusVoidVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

/**
 * 发票
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class InvoiceServiceImpl extends BaseService implements InvoiceService {

    @Override
    public StatusOneVo saveInvoice(InvoiceDto invoiceDto, BindingResult bindingResult) {
        StatusOneVo vo=new StatusOneVo();
        if (bindingResult.hasErrors()){
            vo.fail(ErrorCode.PARAMETER_ERROR, bindingResult.getFieldError().getDefaultMessage());
        }else {
            Invoice invoice = invoiceMapper.dtoToBean(invoiceDto);
            invoice.setUserId(getUser().getId());
            Invoice invoice1 = invoiceRepository.saveAndFlush(invoice);
            vo.success(invoice1.getId());
        }
        return vo;
    }
}
