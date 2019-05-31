package com.zhangzhao.app.mapper;

import com.zhangzhao.app.vo.ReservationTypeVo;
import com.zhangzhao.common.entity.ReservationType;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * 质量处理
 */
@Component
@Mapper(componentModel = "spring")
public interface ReservationTypeMapper {

    ReservationTypeVo beanToVo(ReservationType ReservationType);

}
