package com.zhangzhao.app.mapper;

import com.zhangzhao.app.vo.AddressVo;
import com.zhangzhao.common.dto.AddressDto;
import com.zhangzhao.common.entity.Address;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * 地址
 */
@Component
@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressVo beanToVo(Address address);

    Address dtoToBean(AddressDto addressDto);

}
