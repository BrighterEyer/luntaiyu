package com.zhangzhao.app.mapper;

import com.zhangzhao.app.vo.ComplaintsVo;
import com.zhangzhao.common.entity.Complaints;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * 投诉
 */
@Component
@Mapper(componentModel = "spring")
public interface ComplaintsMapper {

    ComplaintsVo beanToVo(Complaints complaints);
}
