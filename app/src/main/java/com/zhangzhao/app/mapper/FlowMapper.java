package com.zhangzhao.app.mapper;

import com.zhangzhao.app.vo.FlowVo;
import com.zhangzhao.common.entity.Flow;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * 质量处理
 */
@Component
@Mapper(componentModel = "spring")
public interface FlowMapper {

    FlowVo beanToVo(Flow flow);
}
