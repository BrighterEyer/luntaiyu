package com.zhangzhao.web.mapper;

import com.zhangzhao.web.vo.ActivityVo;
import com.zhangzhao.common.entity.Activity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * 发票
 */
@Component
@Mapper(componentModel = "spring")
public interface InvoiceMapper {

    ActivityVo beanToVo(Activity activity);
}
