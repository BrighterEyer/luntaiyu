package com.zhangzhao.web.mapper;

import com.zhangzhao.common.entity.Quality;
import com.zhangzhao.web.vo.QualityVo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * 质量处理
 */
@Component
@Mapper(componentModel = "spring")
public interface QualityMapper {

    QualityVo beanToVo(Quality quality);
}
