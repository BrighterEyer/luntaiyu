package com.zhangzhao.web.mapper;

import com.zhangzhao.web.vo.BankCardVo;
import com.zhangzhao.common.entity.BankCard;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * 银行卡
 */
@Component
@Mapper(componentModel = "spring")
public interface BankCardMapper {

    BankCardVo beanToVo(BankCard bankCard);
}
