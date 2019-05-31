package com.zhangzhao.app.mapper;

import com.zhangzhao.app.vo.PresentDetailsVo;
import com.zhangzhao.app.vo.PresentVo;
import com.zhangzhao.common.entity.CarModel;
import com.zhangzhao.common.entity.Present;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * 礼品
 */
@Component
@Mapper(componentModel = "spring")
public interface PresentMapper {

    PresentVo beanToVo(Present present);

    PresentDetailsVo beanToDetailsVo(Present present);
}
