package com.zhangzhao.web.mapper;

import com.zhangzhao.web.vo.AddressVo;
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
}
