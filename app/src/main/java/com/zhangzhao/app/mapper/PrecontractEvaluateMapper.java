package com.zhangzhao.app.mapper;

import com.zhangzhao.app.vo.PrecontractEvaluateVo;
import com.zhangzhao.common.dto.PrecontractEvaluateDto;
import com.zhangzhao.common.entity.PrecontractEvaluate;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * 预约评价
 *
 * @author Administrator
 */
@Component
@Mapper(componentModel = "spring")
public interface PrecontractEvaluateMapper {
    PrecontractEvaluateVo beanToVo(PrecontractEvaluate precontractEvaluate);

    PrecontractEvaluate dtoToBean(PrecontractEvaluateDto precontractEvaluateDto);

}
