package com.zhangzhao.web.mapper;

import com.zhangzhao.web.vo.CarModelVo;
import com.zhangzhao.common.entity.CarModel;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * 车型
 */
@Component
@Mapper(componentModel = "spring")
public interface CarModelMapper {

    CarModelVo beanToVo(CarModel carModel);
}
