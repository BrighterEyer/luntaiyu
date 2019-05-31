package com.zhangzhao.app.mapper;

import com.zhangzhao.app.vo.ActivityVo;
import com.zhangzhao.app.vo.InvoiceVo;
import com.zhangzhao.common.dto.InvoiceDto;
import com.zhangzhao.common.entity.Activity;
import com.zhangzhao.common.entity.Invoice;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * 发票
 */
@Component
@Mapper(componentModel = "spring")
public interface InvoiceMapper {

    InvoiceVo beanToVo(Invoice invoice);

    Invoice dtoToBean(InvoiceDto invoiceDto);
}
