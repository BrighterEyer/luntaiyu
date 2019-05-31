package com.zhangzhao.app.mapper;

import com.zhangzhao.common.dto.BankCardDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.zhangzhao.app.vo.BankCardVo;
import com.zhangzhao.common.entity.BankCard;

/**
 * 银行卡
 */
@Component
@Mapper(componentModel = "spring")
public interface BankCardMapper {

    BankCardVo beanToVo(BankCard bankCard);

    BankCard DtoTobean(BankCardDto bankCardDto);
}
